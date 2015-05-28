package com.revmob.unity;

import com.revmob.RevMobAdsListener;
import com.unity3d.player.UnityPlayer;

public class RevMobAdsUnityListener extends RevMobAdsListener {
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

    public void onRevMobAdDismissed() {
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
	
	public void onRevMobVideoFinished() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoLoaded", adType);	
	}
	
	public void onRevMobVideoNotCompletelyLoaded() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoNotCompletelyLoaded", adType);	
	}
	
	public void onRevMobVideoLoaded() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoReceived", adType);	
	}
	
	public void onRevMobVideoStarted() {
		UnityPlayer.UnitySendMessage(gameObjectName, "VideoStarted", adType);	
	}

	public void onRevMobRewardedVideoFinished() {
		UnityPlayer.UnitySendMessage(gameObjectName, "RewardedVideoLoaded", adType);	
	}
	
	public void onRevMobRewardedVideoNotCompletelyLoaded() {
		UnityPlayer.UnitySendMessage(gameObjectName, "RewardedVideoNotCompletelyLoaded", adType);	
	}
	
	public void onRevMobRewardedVideoLoaded() {
		UnityPlayer.UnitySendMessage(gameObjectName, "RewardedVideoReceived", adType);	
	}
	
	public void onRevMobRewardedVideoStarted() {
		UnityPlayer.UnitySendMessage(gameObjectName, "RewardedVideoStarted", adType);	
	}

	public void onRevMobRewardedVideoCompleted() {
		UnityPlayer.UnitySendMessage(gameObjectName, "RewardedVideoCompleted", adType);	
	}

	public void onRevMobRewardedPreRollDisplayed() {
		UnityPlayer.UnitySendMessage(gameObjectName, "RewardedPreRollDisplayed", adType);	
	}

}
