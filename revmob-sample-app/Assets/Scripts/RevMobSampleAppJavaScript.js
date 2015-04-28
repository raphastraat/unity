import System.Collections.Generic;

private var APP_IDS = new Dictionary.<String, String>();
private var revmob:RevMob;
private var banner:RevMobBanner;

function Awake() {
    // Just replace the ID below with your appID.
	APP_IDS["Android"] = "4f56aa6e3dc441000e005a20";
	APP_IDS["IOS"] = "4fd619388d314b0008000213";
}

function OnGUI () {
	if (revmob != null) {
		if(GUI.Button(new Rect(20, 20, 300, 50), "Show Fullscreen")) {
			if ( revmob.IsDevice() ) {
				Debug.Log("[RevMob Sample App - JavaScript] Show Fullscreen");
				var fullscreen = revmob.CreateFullscreen();
				fullscreen.Show();
				fullscreen = null;
			}
		}
	
		if(GUI.Button(new Rect(20, 80, 300, 50), "Show Banner")) {
			if ( revmob.IsDevice() ) {
				Debug.Log("[RevMob Sample App - JavaScript] Show Banner");
			#if UNITY_ANDROID
				revmob.ShowBanner();
			#elif UNITY_IPHONE
				banner = revmob.CreateBanner();
				banner.Show();
			#endif
			}
		}
	
		if(GUI.Button(new Rect(20, 140, 300, 50), "Remove Banner")) {
			if ( revmob.IsDevice() ) {
				Debug.Log("[RevMob Sample App - JavaScript] Remove Banner");
			#if UNITY_ANDROID
				revmob.HideBanner();
			#elif UNITY_IPHONE
				banner.Release();
				banner = null;
			#endif
			}
		}
	
		if(GUI.Button(new Rect(20, 200, 300, 50), "Show Popup")) {
			if ( revmob.IsDevice() ) {
				Debug.Log("[RevMob Sample App - JavaScript] Show Popup");
				var popup = revmob.CreatePopup();
				popup.Show();
				popup = null;
			}
		}
	
		if(GUI.Button(new Rect(20, 260, 300, 50), "Open Link")) {
			if ( revmob.IsDevice() ) {
				Debug.Log("[RevMob Sample App - JavaScript] Open Link");
				var adLink = revmob.CreateAdLink();
				adLink.Open();
				adLink = null;
			}
		}
	}
	else {
		if(GUI.Button(new Rect(20, 20, 300, 50), "Start Session")) {
			Debug.Log("[RevMob Sample App - JavaScript] Starting Session");
			revmob = RevMob.Start(APP_IDS, "GameObject");
			if (revmob != null) {
				revmob.PrintEnvironmentInformation();
				revmob.SetTestingMode(RevMob.Test.WITH_ADS);
			}
		}	
	}
}

function SessionIsStarted() {
	Debug.Log("[RevMob Sample App - javascript] Session Started");
}

function SessionNotStarted(message) {
	Debug.Log("[RevMob Sample App - javascript] Session not started");
}

function AdDidReceive(adUnitType) {
	Debug.Log("[RevMob Sample App - JavaScript] Ad did received");
}

function AdDidFail(adUnitType) {
	Debug.Log("[RevMob Sample App - JavaScript] Ad did fail");
}

function AdDisplayed(adUnitType) {
	Debug.Log("[RevMob Sample App - JavaScript] Ad displayed");
}

function UserClickedInTheAd(adUnitType) {
	Debug.Log("[RevMob Sample App - JavaScript] Ad clicked");
}

function UserClosedTheAd(adUnitType) {
	Debug.Log("[RevMob Sample App - JavaScript] Ad closed");
}

function EulaIsShown() {
  Debug.Log("[RevMob Sample App - JavaScript] Eula is Shown");
}
  
function EulaAccepted() {
  Debug.Log("[RevMob Sample App - JavaScript] Eula accepted");
}
    
function EulaRejected() {
  Debug.Log("[RevMob Sample App - JavaScript] Eula rejected");
}


