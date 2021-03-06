package com.app.epolice.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * The type Sms notification.
 */
@Service
public class SmsNotification {
    private final String ACCOUNT_SID ="AC899fa2ea88ed71b93e716ffb0135a969";
    private final String AUTH_TOKEN = "3725f7782a82bb88474734f0b844095e";
    private final String FROM_NUMBER = "+17242515324";

    /**
     * Sending the sms notification to the specific mobile number
     *
     * @param toNumber    the to number
     * @param userMessage the user message
     * @return response entity
     */
    public ResponseEntity<Object> Notification(String toNumber, String userMessage){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(toNumber), new PhoneNumber(FROM_NUMBER), userMessage)
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction
        return new ResponseEntity<>("The message has been successfully sent to: "+toNumber, HttpStatus.OK);
    }

}
