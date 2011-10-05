package com.training.asynctaskexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AsyncTaskExampleActivity extends Activity implements OnClickListener{
	
	private Boolean success = true;
	private static AsyncTaskExampleActivity MainActivityInstance;
	private CallBack c;
	ProgressDialog progressDialog;
	Button startAsyncTask;
	MyAsyncTask aTask;
	Button cancelAsyncTask;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startAsyncTask = (Button)findViewById(R.id.button1);
        cancelAsyncTask = (Button)findViewById(R.id.button2);
        startAsyncTask.setOnClickListener(this);
        cancelAsyncTask.setOnClickListener(this);
        MainActivityInstance = this;
        
        //ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this.getApplicationContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("On Progress...");
        progressDialog.setCancelable(false);
        
        c = new CallBack() {
        	public void onProgress(){
        		//progressDialog.show();
        		Toast toast = Toast.makeText(getMainActivity().getApplicationContext(), "Progress!!", 1000);
	        	toast.show();
        	}
        	
        	public void onResult(Boolean result){
        		if(result.equals(true)){
        			Toast toast = Toast.makeText(getMainActivity().getApplicationContext(), "Bingo...Success!!", 1000);
    	        	toast.show();
        		}
        		
        		else {
        			Toast toast = Toast.makeText(getMainActivity().getApplicationContext(), "Alas!! Failure", 1000);
                	toast.show();
        		}
        	}
        	
        	public void onCancel(Boolean result){
        		Toast toast = Toast.makeText(getMainActivity().getApplicationContext(), "Cancelled", 1000);
            	toast.show();
        	}
        };
        
        aTask = new MyAsyncTask(c);
    }   
    static AsyncTaskExampleActivity getMainActivity(){
    	return MainActivityInstance;
    }
    
    public Boolean getSuccessOrFailureResult(){
    	return success;
    }
    
    public void onClick(View v){
    	if(v.equals(startAsyncTask)){
    		aTask.execute("Start");
    	}
    	if(v.equals(cancelAsyncTask)){
    		aTask.cancel(true);
    	}
    }
}