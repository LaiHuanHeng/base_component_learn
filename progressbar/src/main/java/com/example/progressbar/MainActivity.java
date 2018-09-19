package com.example.progressbar;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private TasksCompletedView mTasksView;

    private int mTotalProgress;
    private int mCurrentProgress;
    private RoundProgressBar rpBar01, rpBar02 ,rpBar03, rpBar04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariable();
        viewInit();

        new Thread(new ProgressRunable()).start();
    }

    private void initVariable() {
        mTotalProgress = 100;
        mCurrentProgress = 0;
    }

    private void viewInit() {
        //mTasksView = (TasksCompletedView) findViewById(R.id.tasks_view);

        rpBar01 = (RoundProgressBar) findViewById(R.id.roundProgressBar01_id);
        rpBar02 = (RoundProgressBar) findViewById(R.id.roundProgressBar02_id);
        rpBar03 = (RoundProgressBar) findViewById(R.id.roundProgressBar03_id);
        rpBar04 = (RoundProgressBar) findViewById(R.id.roundProgressBar04_id);
    }

    class ProgressRunable implements Runnable {

        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress) {
                mCurrentProgress += 1;

//                mTasksView.setProgress(mCurrentProgress);

                rpBar01.setProgress(mCurrentProgress);
                rpBar02.setProgress(mCurrentProgress);
                rpBar03.setProgress(mCurrentProgress);
                rpBar04.setProgress(mCurrentProgress);

                try {
                    Thread.sleep(100);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
