READ THIS BEFORE BUILDING OR EDITING THE CODE
=============================================

Configuring environment
-----------------------

1) Download Unity 4.2.2 from Unity website (Unity 4.3 had some problems).

Releasing
--------

1) Verify that you have the Ruby language installed in your machine (type in the terminal: "ruby -v")

2) Download aws-sdk from Amazon website (ruby) and define the following environment variables: AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_REGION. Set them to your Amazon account values.

3) Android
- copy the library (sdk-android generated .jar) to revmob-android-wrapper/lib.
- open android project (revmob-android-wrapper) into eclipse and relink the lib (since the version has changed): delete the link and create it again.

4) iOS
- copy RevMobAds from iOS SDK into unity-sdk/Assets/Plugins/iOS and rename to RevMobAds.a.
- copy headers from iOS SDK to unity-sdk/Assets/Plugins/iOS.


5) rake build: run task to generate sample app and package. Do not forget to update update manually the last and the new SDK versions at the top of rakefile.

6) rake upload: upload package to S3.