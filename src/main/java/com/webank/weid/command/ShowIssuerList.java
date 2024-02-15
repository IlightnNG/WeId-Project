package com.webank.weid.command;

import com.beust.jcommander.JCommander;
import com.webank.weid.constant.ErrorCode;
import com.webank.weid.dto.Issuer;
import com.webank.weid.dto.PageDto;
import com.webank.weid.protocol.base.AuthorityIssuer;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.AuthorityIssuerService;
import com.webank.weid.service.WeIdSdkService;
import com.webank.weid.service.impl.AuthorityIssuerServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class ShowIssuerList {
    private static WeIdSdkService weIdSdkService = new WeIdSdkService();
    public static void main(String[] args) {

        if (args == null || args.length < 4) {
            log.error(
                    "[ShowIssuerList] input parameters error, please check your input!");
            System.exit(1);
        }

        CommandArgs commandArgs = new CommandArgs();
        JCommander.newBuilder()
                .addObject(commandArgs)
                .build()
                .parse(args);

        //log提示
        log.info("[ShowIssuerList] begin to show issuer.");

        //获取index和num
        int index = commandArgs.getIndex();
        int num = commandArgs.getNum();

        PageDto<Issuer> pageDto = new PageDto<>(index,num);

        //展示所有发行方列表
        ResponseData<PageDto<Issuer>> response = weIdSdkService.getIssuerList(pageDto);

        //输出信息
        if (response.getErrorCode() != ErrorCode.SUCCESS.getCode()) {
            System.out.println("[ShowIssuerList] Show issuer list failed, result: " + response.getErrorMessage());
            System.exit(1);
        }

        System.out.println("-----------------------------");
        System.out.println(response.getResult().getDataList());
        System.out.println("-----------------------------");

        System.exit(0);


    }
}
