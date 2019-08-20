package com.zhongxing.task;

import android.util.Log;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.zhongxing.elemerobot.Constants;

import java.util.List;

/**
 * Created by xulun on 2019/6/18.
 */

public class TaskService {
    private static final String TAG = "TaskService";
    private static TaskService mInstance = null;
    private AWSCredentials credentials;
    private AmazonSQSClient sqsClient;
//    private String queue_name;
//    private String task_queue_url;

    public TaskService(){
        this.credentials = new BasicAWSCredentials(Constants.Access_Key_Id,Constants.Access_Key_Secret);
        this.sqsClient = new AmazonSQSClient(this.credentials);
        Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
        this.sqsClient.setRegion(region);
//        this.queue_name = "";
//        this.task_queue_url = "";
    }

    public static synchronized TaskService getInstance(){
        if (mInstance==null){
            mInstance = new TaskService();
        }
        return mInstance;
    }

    TaskGroup getTaskEx(){
        return this.getTask();
    }

    public TaskGroup getTask(){
        Log.i(TAG, "getTask: ");
        String queueUrl = Constants.getQueueUrl() + Constants.QUEUE_NAME;
        ReceiveMessageRequest rmr = new ReceiveMessageRequest(queueUrl);
        rmr.setMaxNumberOfMessages(1);
        rmr.setVisibilityTimeout(30);
        try {
            ReceiveMessageResult result = this.sqsClient.receiveMessage(rmr);
            for (int i = 0; i < result.getMessages().size(); ++i) {
                Message message = result.getMessages().get(i);
                String body = message.getBody();
                Log.d(TAG, "getTask:" + body);
                try {
//                    解析任务
                    return TaskGroup.parse(body);
                }catch (Exception ex){
                    Log.e(TAG, "getTask: "+body);
                    Log.e(TAG, "getTask: "+ex);
                }finally {
//                    DeleteMessageRequest request = new DeleteMessageRequest(queueUrl,message.getReceiptHandle());
//                    this.sqsClient.deleteMessage(request);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "getTask: "+ e.getMessage() );
            e.printStackTrace();
        }
        return null;
    }

}
