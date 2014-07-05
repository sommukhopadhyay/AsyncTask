package com.training.asynctaskexample;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {
	
	private CallBack cb;
	Boolean running = true;
	MyAsyncTask(Context context, CallBack cb){
	
		this.cb = cb;
		this.context = context;
	}
	
	protected Boolean doInBackground(String... params){
		
			while(running){
				
				int i = 0;
				try{
					//for (int i = 0; i<5; i++){
					do{
						//Thread.sleep(10000,0);
						publishProgress();
						Thread.sleep(3000,0);
						i++;
					}while(i<5);
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
		if(context != null){
			cb.onResult(result);	
		}
	
	}
	
	protected void onCancelled(){
		
		running = false;
		cb.onCancel(true);
		
	}

}
