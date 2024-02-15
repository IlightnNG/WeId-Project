package com.webank.weid.command;

import com.beust.jcommander.JCommander;
import com.webank.weid.constant.ErrorCode;
import com.webank.weid.dto.Issuer;
import com.webank.weid.dto.PageDto;
import com.webank.weid.protocol.base.AuthorityIssuer;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.AuthorityIssuerService;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.WeIdSdkService;
import com.webank.weid.service.impl.AuthorityIssuerServiceImpl;
import com.webank.weid.service.impl.WeIdServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ShowWeIdOrIssuer {

    public static void main(String[] args) {

        if (args == null || args.length < 2) {
            log.error(
                    "[ShowWeIdOrIssuer] input parameters error, please check your input!");
            System.exit(1);
        }

        CommandArgs commandArgs = new CommandArgs();
        JCommander.newBuilder()
                .addObject(commandArgs)
                .build()
                .parse(args);


        if (args.length == 2) {

            //log提示
            log.info("[ShowWeId] begin to show WeId.");

            WeIdService weIdService = new WeIdServiceImpl();

            //String testWeId = "did:weid:1:0xa8c5d0b6336755882582402b24e21ebe810320da";
            String testWeId = commandArgs.getWeid();

            //show json of weid document
            ResponseData<String> responseJson = weIdService.getWeIdDocumentJson(testWeId);

            //输出失败信息
            if (responseJson.getErrorCode() != ErrorCode.SUCCESS.getCode()) {
                System.out.println("[Show WeId] Show WeId failed, result: " + responseJson.getErrorMessage());
                System.exit(1);
            }

            System.out.println("-----------------------------");
            System.out.println(responseJson.getResult());
            System.out.println("-----------------------------");

            System.exit(0);


        } else if (args.length == 4) {
            //log提示
            log.info("[ShowIssuer] begin to show issuer.");

            WeIdSdkService weIdSdkService = new WeIdSdkService();
            //展示所有发行方列表

            int index = commandArgs.getIndex();
            int num = commandArgs.getNum();

            PageDto<Issuer> pageDto = new PageDto<>(index,num);

            ResponseData<PageDto<Issuer>> response = weIdSdkService.getIssuerList(pageDto);
            //输出信息
            if (response.getErrorCode() != ErrorCode.SUCCESS.getCode()) {
                System.out.println("[ShowIssuer] Show issuer failed, result: " + response.getErrorMessage());
                System.exit(1);
            }

            System.out.println("-----------------------------");
            System.out.println(response.getResult().getDataList());
            System.out.println("-----------------------------");

            System.exit(0);
        }
    }
}
