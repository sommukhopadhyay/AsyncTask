package com.training.asynctaskexample;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {
	
	private CallBack cb;
	
	MyAsyncTask(CallBack cb){
	
		this.cb = cb;
	}
	
	protected Boolean doInBackground(String... params){
		
		for (int i = 0; i<5; i++){
			
			try{
				Thread.sleep(10000,0);
				if(i == 4) throw new InterruptedException();
			}
			catch(InterruptedException e){
				
				return false;
			}
			publishProgress();
			
		}
		return true;
	}
	
	protected void onProgressUpdate(Integer... progress){
		cb.onProgress();
	}
	
	protected void onPostExecute(Boolean result){
	
		cb.onResult(result);
		
	}

}
