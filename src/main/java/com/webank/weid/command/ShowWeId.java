package com.webank.weid.command;

import com.webank.weid.constant.ErrorCode;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.impl.WeIdServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShowWeId {

    //还在思考有必要将此方法移至weIdSdkService中
    private static WeIdService weIdService = new WeIdServiceImpl();

    public static void main(String[] args) {

        String testWeId = "did:weid:1:0xa8c5d0b6336755882582402b24e21ebe810320da";

        //log提示
        log.info("[ShowWeId] begin to show WeId.");

        //WeIdService weIdService = new WeIdServiceImpl();

        //show json of weid document
        ResponseData<String> responseJson = weIdService.getWeIdDocumentJson(testWeId);
        System.out.println("-----------------------------");
        System.out.println(responseJson.getResult());
        System.out.println("-----------------------------");

        //输出信息
        if (responseJson.getErrorCode() != ErrorCode.SUCCESS.getCode()) {
            System.out.println("[Show WeId] Show WeId failed, result: " + responseJson.getErrorMessage());
            System.exit(1);
        }
        System.exit(0);
    }
}
