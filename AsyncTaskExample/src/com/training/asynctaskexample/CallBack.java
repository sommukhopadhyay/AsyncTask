package com.training.asynctaskexample;

public interface CallBack {
	
	public void onProgress();
	public void onResult(Boolean result);
	public void onCancel(Boolean result);
}
