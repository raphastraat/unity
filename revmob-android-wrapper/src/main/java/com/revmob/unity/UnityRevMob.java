package com.revmob.unity;

import android.app.Activity;
import android.view.Gravity;

import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.RevMobTestingMode;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.ads.fullscreen.RevMobFullscreen;
import com.revmob.ads.link.RevMobLink;
import com.revmob.ads.popup.RevMobPopup;
import com.revmob.client.RevMobClient;

public class UnityRevMob {
	protected static RevMob session;
	protected static UnityRevMob unitySession;
	protected static boolean bannerLoaded;

	public static UnityRevMob start(final Activity activity, String appId, String sdkName, String sdkVersion, final RevMobAdsUnityListener unityListener) {
		if (session == null) {
			RevMobClient.setSDKName(sdkName);
			RevMobClient.setSDKVersion(sdkVersion);
			session = RevMob.startWithListenerForWrapper(activity, appId, unityListenerToAndroidListener(unityListener));
		}
		if (unitySession == null) {
			unitySession = new UnityRevMob();
		}
		bannerLoaded = false;
		return unitySession;
	}

	protected static RevMobAdsListener unityListenerToAndroidListener(final RevMobAdsUnityListener unityListener) {
		RevMobAdsListener androidListener = new RevMobAdsListener() {
			public void onRevMobSessionIsStarted() {
				if (unityListener != null) {
					unityListener.onRevMobSessionIsStarted();
				}
			}

			public void onRevMobSessionNotStarted(String message) {
				if (unityListener != null) {
					unityListener.onRevMobSessionNotStarted(message);
				}
			}

			public void onRevMobAdNotReceived(String message) {
				if (unityListener != null) {
					unityListener.onRevMobAdNotReceived(message);
				}
			}

			public void onRevMobAdReceived() {
				if (unityListener != null) {
					unityListener.onRevMobAdReceived();
				}
			}

			public void onRevMobAdDisplayed() {
				if (unityListener != null) {
					unityListener.onRevMobAdDisplayed();
				}
			}

			public void onRevMobAdClicked() {
				if (unityListener != null) {
					unityListener.onRevMobAdClicked();
				}
			}

			public void onRevMobAdDismiss() {
				if (unityListener != null) {
					unityListener.onRevMobAdDismiss();
				}
			}

			public void onRevMobEulaIsShown() {
				if (unityListener != null) {
					unityListener.onRevMobEulaIsShown();
				}				
			}

			public void onRevMobEulaWasAcceptedAndDismissed() {
				if (unityListener != null) {
					unityListener.onRevMobEulaWasAcceptedAndDismissed();
				}
			}

			public void onRevMobEulaWasRejected() {
				if (unityListener != null) {
					unityListener.onRevMobEulaWasRejected();
				}	
			}
			
			public void onRevMobVideoCompleted() {
				if (unityListener != null) {
					unityListener.onRevMobVideoCompleted();
				}
			}
			
			public void onRevMobVideoDismiss() {
				if (unityListener != null) {
					unityListener.onRevMobVideoDismiss();
				}
			}
			public void onRevMobVideoNotCompletelyLoaded() {
				if (unityListener != null) {
					unityListener.onRevMobVideoNotCompletelyLoaded();
				}
			}
			public void onRevMobVideoReceived() {
				if (unityListener != null) {
					unityListener.onRevMobVideoReceived();
				}
			}
			public void onRevMobVideoStarted() {
				if (unityListener != null) {
					unityListener.onRevMobVideoStarted();
				}
			}
		};
		return androidListener;
	}

	public RevMobFullscreen showFullscreen(final Activity activity, String placementId, final RevMobAdsUnityListener unityListener) {
		RevMobFullscreen ad = createFullscreen(activity, placementId, unityListener);
		ad.show();
		return ad;
	}

	public RevMobFullscreen createFullscreen(final Activity activity, String placementId, final RevMobAdsUnityListener unityListener) {
		return session.createFullscreen(activity, placementId, unityListenerToAndroidListener(unityListener));
	}

	public RevMobLink openAdLink(final Activity activity, String placementId, final RevMobAdsUnityListener unityListener) {
		RevMobLink ad = createAdLink(activity, placementId, unityListener);
		ad.open();
		return ad;
	}

	public RevMobLink createAdLink(final Activity activity, String placementId, final RevMobAdsUnityListener unityListener) {
		return session.createAdLink(activity, placementId, unityListenerToAndroidListener(unityListener));
	}

	public void createBanner(final Activity activity, final RevMobAdsUnityListener unityListener, int position, int x, int y, int w, int h) {
		releaseBanner();
		
  	if (x < 0) {
  		x = 0;
  	}
  	if (y < 0) { 
  		y = 0;
  	}
    if (w == 0) {//(w < RevMobBanner.DEFAULT_WIDTH_IN_DIP) {
      w = activity.getWindowManager().getDefaultDisplay().getWidth();
    }
    if (h == 0) { //(h < RevMobBanner.DEFAULT_HEIGHT_IN_DIP) {
      h = w * RevMobBanner.DEFAULT_HEIGHT_IN_DIP / RevMobBanner.DEFAULT_WIDTH_IN_DIP;
    }
		
		int gravity = (position == 0) ? Gravity.BOTTOM : Gravity.TOP;
		session.preloadBanner(activity, gravity, x, y, w, h, null, unityListener);
		bannerLoaded = true;
	}
	
	public void showBanner() {
		if (bannerLoaded) {
			while (!session.showLoadedBanner()) {
				//TODO: Refactor this code
			}
		}
	}
	
	public void showBanner(final Activity activity, final RevMobAdsUnityListener unityListener, int position, int x, int y, int w, int h) {
		createBanner(activity, unityListener, position, x, y, w, h);
		showBanner();
	}

	public void hideBanner() {
		if (bannerLoaded) {
			session.hideLoadedBanner();
		}
	}
	
	public void hideBanner(final Activity activity) {
		hideBanner();
	}
	
	public void releaseBanner() {
		hideBanner();
		bannerLoaded = false;
		session.releaseLoadedBanner();
	}

	public RevMobPopup showPopup(final Activity activity, String placementId, final RevMobAdsUnityListener unityListener) {
		RevMobPopup ad = createPopup(activity, placementId, unityListener);
		ad.show();
		return ad;
	}

	public RevMobPopup createPopup(final Activity activity, String placementId, final RevMobAdsUnityListener unityListener) {
		return session.createPopup(activity, placementId, unityListenerToAndroidListener(unityListener));
	}

	public void setTestingMode(int testingMode) {
		switch(testingMode) {
		case 0:
			session.setTestingMode(RevMobTestingMode.DISABLED);
			break;
		case 1:
			session.setTestingMode(RevMobTestingMode.WITH_ADS);
			break;
		case 2:
			session.setTestingMode(RevMobTestingMode.WITHOUT_ADS);
			break;
		default:
			session.setTestingMode(RevMobTestingMode.DISABLED);
			break;
		}
	}

	public void setTimeoutInSeconds(int timeoutInSeconds) {
		session.setTimeoutInSeconds(timeoutInSeconds);
	}

	public void printEnvironmentInformation(final Activity activity) {
		session.printEnvironmentInformation(activity);
	}
}
