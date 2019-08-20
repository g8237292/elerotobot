package com.zhongxing.elemerobot;

import android.util.Log;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;

/**
 * Created by xulun on 2019/6/17.
 */

public class Test {
    private static final String TAG = "Test";
    
    public Test(){
        CloudAccount account = new CloudAccount(
                "LTAIb1i8bTq65QnZ",
                "zfxXGEDUdK7jXTPIAsMnqZqNTcmrub",
                "http://1178181793937081.mns.cn-hangzhou.aliyuncs.com/");

        MNSClient client  = account.getMNSClient();            //this client need only initialize once
        //循环消费10条消息
        try{
            CloudQueue queue = client.getQueueRef("ceshi");
            for (int i = 0; i < 10; i++)
            {
                Message popMsg = queue.popMessage();
                if (popMsg != null){
                    Log.d(TAG, "Test:message handle: " + popMsg.getReceiptHandle());
                    // 默认会做 base64 解码
                    Log.d(TAG, "Test:message body: " + popMsg.getMessageBodyAsString());
                    // 消息 body 的原始数据，不做 base64 解码
                    // android.util.Log.d(TAG, "Test: ");("message body: " + popMsg.getMessageBodyAsRawString ());
                    Log.d(TAG, "Test:message id: " + popMsg.getMessageId());
                    Log.d(TAG, "Test: message dequeue count:" + popMsg.getDequeueCount());
                    //删除已经消费的消息
                    queue.deleteMessage(popMsg.getReceiptHandle());
                    Log.d(TAG, "Test:delete message successfully.\n");
                }
                else{
                    Log.d(TAG, "Test: message not exist in TestQueue.\n");
                }
            }
        } catch (ClientException ce)
        {
            Log.d(TAG, "Test: Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            ce.printStackTrace();
        } catch (ServiceException se)
        {
            se.printStackTrace();
//                System.out.print("MNS exception requestId:" + se.getRequestId(), se);
            if (se.getErrorCode() != null) {
                if (se.getErrorCode().equals("QueueNotExist"))
                {
                    Log.d(TAG, "Test: Queue is not exist.Please create before use");
                } else if (se.getErrorCode().equals("TimeExpired"))
                {
                    Log.d(TAG, "Test: The request is time expired. Please check your local machine timeclock");
                }
            /*
            you can get more MNS service error code from following link:
            https://help.aliyun.com/document_detail/mns/api_reference/error_code/error_code.html
            */
            }
        } catch (Exception e)
        {
            Log.d(TAG, "Test: Unknown exception happened!");
            e.printStackTrace();
        }
        client.close();
    }
    
}
