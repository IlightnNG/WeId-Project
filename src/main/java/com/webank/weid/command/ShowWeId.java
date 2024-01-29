package com.webank.weid.command;

import com.webank.weid.protocol.base.WeIdDocument;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.base.WeIdPublicKey;
import com.webank.weid.protocol.request.PublicKeyArgs;
import com.webank.weid.protocol.request.SetPublicKeyArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.WeIdSdkService;
import com.webank.weid.service.impl.WeIdServiceImpl;
import com.webank.weid.util.WeIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;

import java.io.File;
import java.security.KeyPair;
import java.security.PublicKey;

@Slf4j
public class ShowWeId {


    public static void main(String[] args) {

        String testWeId = "did:weid:1:0xa8c5d0b6336755882582402b24e21ebe810320da";

        //log提示
        log.info("[ShowWeId] begin to show WeId.");

        WeIdService weIdService = new WeIdServiceImpl();

        //show json of weid document
        ResponseData<String> responseJson = weIdService.getWeIdDocumentJson(testWeId);
        System.out.println(responseJson);
        System.out.println("-----------------------------");
        //show weid document
        //ResponseData<WeIdDocument> response = weIdService.getWeIdDocument("did:weid:1:0xa8c5d0b6336755882582402b24e21ebe810320da");
        //System.out.println(response);


    }
}
