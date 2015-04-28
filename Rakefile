MAIN_PATH = File.dirname(__FILE__)

LAST_VERSION = "7.4.2"
VERSION = "7.5.0"
UNITY = "/Applications/Unity/Unity.app/Contents/MacOS/Unity"
UNITY_PACKAGE_FILE = "revmob-unity-sdk-#{VERSION}.unitypackage"
PACKAGE_FILE = "revmob-unity-sdk-#{VERSION}.zip"
SDK_PATH = MAIN_PATH + "/unity-sdk"
TMP_DIR = "revmob-unity-sdk"
ANDROID_WRAPPER = MAIN_PATH + "/revmob-android-wrapper"
SAMPLE_APP = "./revmob-sample-app"

def colorize(text, color)
  color_codes = {
    :black    => 30,
    :red      => 31,
    :green    => 32,
    :yellow   => 33,
    :blue     => 34,
    :magenta  => 35,
    :cyan     => 36,
    :white    => 37
  }
  code = color_codes[color]
  if code == nil
    text
  else
    "\033[#{code}m#{text}\033[0m"
  end
end

def unity_build(package_file)
  sh "#{UNITY} -batchmode -quit -exportPackage Assets/Plugins #{package_file} -logFile build_error.log"
end

task :clean_workspace do
  sh "rm -rf Assembly-*"
  sh "rm -rf *.sln"
  sh "rm -rf *.userprefs"
  sh "rm -f *.unitypackage"
  sh "rm -rf Library/"
  sh "rm -rf #{SDK_PATH}/Library/"
  sh "rm -rf #{SDK_PATH}/Temp/"
end

task :clean do
  sh "rm -rf #{UNITY_PACKAGE_FILE} #{PACKAGE_FILE} #{TMP_DIR}"
end

task :build_android_wrapper do
  sh "cd #{ANDROID_WRAPPER} && mvn -q clean package && cd -"
  sh "cp #{ANDROID_WRAPPER}/target/revmob-android-wrapper.jar #{SDK_PATH}/Assets/Plugins/Android"
  puts colorize("RevMob Unity-Android SDK Wrapper created: #{ANDROID_WRAPPER}/target/revmob-android-wrapper.jar", :blue)
end

task :update_sample_app do
  sh "rm -rf #{SAMPLE_APP}/Assets/"
  sh "cp -r #{SDK_PATH}/Assets/ #{SAMPLE_APP}/Assets/"
  puts colorize("Unity Sample App updated", :blue)
end

task :build => [:clean, :build_android_wrapper, :update_sample_app] do
  sh "cd #{SDK_PATH}"
  begin
    sh "mv #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml.example"
    sh "mv #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml.meta #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml.example.meta"
    unity_build("#{MAIN_PATH}/#{UNITY_PACKAGE_FILE}")
    sh "cd #{MAIN_PATH}"
    puts colorize("File created: #{UNITY_PACKAGE_FILE}", :blue)
    sh "mkdir #{TMP_DIR}"
    sh "mv #{UNITY_PACKAGE_FILE} #{TMP_DIR}/"
    sh "cp -r #{SAMPLE_APP} #{TMP_DIR}"
    sh "rm -f #{TMP_DIR}/*.meta"
    sh "zip -r #{PACKAGE_FILE} #{TMP_DIR}"
    sh "rm -rf #{TMP_DIR}"
    puts colorize("File created: #{PACKAGE_FILE}", :blue)
  rescue
    puts colorize("Error in Unity build", :red)
    sh "cat build_error.log"
    raise
  ensure
    sh "mv #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml.example #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml"
    sh "mv #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml.example.meta #{SDK_PATH}/Assets/Plugins/Android/AndroidManifest.xml.meta"
  end
end

task :upload => [:build] do
  filename = PACKAGE_FILE
  puts colorize("Uploading to S3", :blue)
  require 'aws-sdk'
  AWS.config(access_key_id: ENV["AWS_ACCESS_KEY_ID"], secret_access_key: ENV["AWS_SECRET_ACCESS_KEY"], region: ENV["AWS_REGION"])
  bucket = AWS.s3.buckets["sdks"]
  if not bucket.exists? then
    bucket = AWS.s3.buckets.create("sdks", :acl => :public_read)
    bucket.configure_website
  end
  key = File.basename(filename)
  obj = bucket.objects[key]
  obj.write(:file => filename, :acl => :public_read)
  puts obj.public_url
  puts colorize("Upload finished", :green)
end

desc "Change Version"
task :version_upgrade do |t, args|
  # revmobSample = File.join(MAIN_PATH, "revmob-sample-app", "Assets", "Plugins", "RevMob.cs")
  revmobSDK = File.join(MAIN_PATH, "unity-sdk", "Assets", "Plugins", "RevMob.cs")
  
  from_version = LAST_VERSION
  to_version = VERSION
  
  file_names = [revmobSDK]
  
  file_names.each do |file_name|
    text = File.read(file_name)
    text.gsub!(from_version, to_version)
    File.open(file_name, "w") {|file| file.puts text}
  end
end


task :default => [:version_upgrade, :build]
