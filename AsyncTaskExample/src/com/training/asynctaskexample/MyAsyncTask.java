package com.training.asynctaskexample;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {
	
	private CallBack cb;
	Boolean running = true;
	MyAsyncTask(CallBack cb){
	
		this.cb = cb;
	}
	
	protected Boolean doInBackground(String... params){
		
			while(running){
				try{
					for (int i = 0; i<5; i++){
						Thread.sleep(10000,0);
						publishProgress();
					}
				}
					catch(InterruptedException e){
						return false;
					}			
				
				return true;
		}
		return false;
	}
	
	protected void onProgressUpdate(Integer... progress){
		cb.onProgress();
	}
	
	protected void onPostExecute(Boolean result){
	
		cb.onResult(result);
		
	}
	
	protected void onCancelled(){
		
		running = false;
		cb.onCancel(true);
		
	}

}
