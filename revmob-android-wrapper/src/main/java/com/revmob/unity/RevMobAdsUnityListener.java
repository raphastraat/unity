package com.revmob.unity;

import com.revmob.RevMobAdsListener;
import com.unity3d.player.UnityPlayer;

public class RevMobAdsUnityListener implements RevMobAdsListener {
    private static final String DEFAULT_GAME_OBJECT_NAME = "GameObject";
    private String gameObjectName;
    private String adType;

    public RevMobAdsUnityListener(String gameObjectName, String adType) {
        this.gameObjectName = (gameObjectName == null) ? DEFAULT_GAME_OBJECT_NAME : gameObjectName;
        this.adType = adType;
    }

    public void onRevMobSessionIsStarted() {
    	UnityPlayer.UnitySendMessage(gameObjectName, "SessionIsStarted", adType);
    }

    public void onRevMobSessionNotStarted(String message) {
    	UnityPlayer.UnitySendMessage(gameObjectName, "SessionNotStarted", adType);
    }

    public void onRevMobAdNotReceived(String message) {
    	UnityPlayer.UnitySendMessage(gameObjectName, "AdDidFail", adType);
    }

    public void onRevMobAdReceived() {
        UnityPlayer.UnitySendMessage(gameObjectName, "AdDidReceive", adType);
    }

    public void onRevMobAdDisplayed() {
    	UnityPlayer.UnitySendMessage(gameObjectName, "AdDisplayed", adType);
    }

    public void onRevMobAdClicked() {
        UnityPlayer.UnitySendMessage(gameObjectName, "UserClickedInTheAd", adType);
    }

    public void onRevMobAdDismiss() {
    	UnityPlayer.UnitySendMessage(gameObjectName, "UserClosedTheAd", adType);
    }

	public void onRevMobEulaIsShown() {
		UnityPlayer.UnitySendMessage(gameObjectName, "EulaIsShown", adType);	
	}

	public void onRevMobEulaWasAcceptedAndDismissed() {
		UnityPlayer.UnitySendMessage(gameObjectName, "EulaAccepted", adType);	
	}

	public void onRevMobEulaWasRejected() {
		UnityPlayer.UnitySendMessage(gameObjectName, "EulaRejected", adType);	
	}
	
	public void onRevMobVideoCompleted() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoCompleted", adType);	
	}
	
	public void onRevMobVideoDismiss() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoDismiss", adType);	
	}
	
	public void onRevMobVideoNotCompletelyLoaded() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoNotCompletelyLoaded", adType);	
	}
	
	public void onRevMobVideoReceived() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoReceived", adType);	
	}
	
	public void onRevMobVideoStarted() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoStarted", adType);	
	}

}
