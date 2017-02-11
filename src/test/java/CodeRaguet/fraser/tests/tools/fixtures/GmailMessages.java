package CodeRaguet.fraser.tests.tools.fixtures;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartBody;
import com.google.api.services.gmail.model.MessagePartHeader;

import java.math.BigInteger;
import java.util.Arrays;

import static java.util.Collections.singletonList;

public class GmailMessages {

    public static final String FIRST_GMAIL_MESSAGE_HTML_DATA = "PGRpdiBkaXI9Imx0ciI-PHNwYW4gc3R5bGU9ImNvbG9yOnJnYigzMywzMywzMyk7Zm9udC1mYW1pbHk6dmVyZGFuYSxzYW5zLXNlcmlmO2ZvbnQtc2l6ZToxMy4zMzMzcHgiPkVsIGluZmllcm5vIGVzIGVsIG9sdmlkbzwvc3Bhbj48YnI-PC9kaXY-DQo=";

    public static Message firstGmailMessage() {
        Message gmailMessage = new Message();
        gmailMessage.setHistoryId(BigInteger.valueOf(8170));
        gmailMessage.setId("15946741e5510f9a");
        gmailMessage.setInternalDate(1482945720000L);
        gmailMessage.setLabelIds(Arrays.asList("IMPORTANT", "CATEGORY_PERSONAL", "INBOX"));
        gmailMessage.setPayload(new MessagePart()
                .setBody(new MessagePartBody().setSize(0))
                .setFilename("")
                .setHeaders(Arrays.asList(
                        new MessagePartHeader().setName("Delivered-To").setValue("fraser.quote@gmail.com"),
                        new MessagePartHeader().setName("Received").setValue("by 10.159.55.196 with SMTP id q62csp5627226uaq;        Wed, 28 Dec 2016 09:22:12 -0800 (PST)"),
                        new MessagePartHeader().setName("X-Received").setValue("by 10.194.76.193 with SMTP id m1mr37081355wjw.70.1482945732388;        Wed, 28 Dec 2016 09:22:12 -0800 (PST)"),
                        new MessagePartHeader().setName("Return-Path").setValue("<ignacio.code@gmail.com>"),
                        new MessagePartHeader().setName("Received").setValue("from mail-wm0-x232.google.com (mail-wm0-x232.google.com. [2a00:1450:400c:c09::232])        by mx.google.com with ESMTPS id b143si21258544wmb.45.2016.12.28.09.22.12        for <fraser.quote@gmail.com>        (version=TLS1_2 cipher=ECDHE-RSA-AES128-GCM-SHA256 bits=128/128);        Wed, 28 Dec 2016 09:22:12 -0800 (PST)"),
                        new MessagePartHeader().setName("Received-SPF").setValue("pass (google.com: domain of ignacio.code@gmail.com designates 2a00:1450:400c:c09::232 as permitted sender) client-ip=2a00:1450:400c:c09::232;"),
                        new MessagePartHeader().setName("Authentication-Results").setValue("mx.google.com;       dkim=pass header.i=@gmail.com;       spf=pass (google.com: domain of ignacio.code@gmail.com designates 2a00:1450:400c:c09::232 as permitted sender) smtp.mailfrom=ignacio.code@gmail.com;       dmarc=pass (p=NONE dis=NONE) header.from=gmail.com"),
                        new MessagePartHeader().setName("Received").setValue("by mail-wm0-x232.google.com with SMTP id m1so26283602wme.0        for <fraser.quote@gmail.com>; Wed, 28 Dec 2016 09:22:12 -0800 (PST)"),
                        new MessagePartHeader().setName("DKIM-Signature").setValue("v=1; a=rsa-sha256; c=relaxed/relaxed;        d=gmail.com; s=20161025;        h=mime-version:from:date:message-id:subject:to;        bh=xVesd8rXeQ4tlqf6bRlB3HAki1/L7uTmJ9M1mKyzLLQ=;        b=ii6fCuuZw3vvssVoxBB/2KhU092n7e68ua2Y8b7B/JtOo50W7KQjQVU0Q7OvxLMu5g         HR0mxyDp+aOpJLJGJQxGjUZ5g8mQId8Xd3pGk9JB7uxqmw0PmpqBjA4hoz5sqe4MDygM         c+E4Rbg1WYmPThNQNkPbpP54/KWY13RAFWb5HpEzE/scE5N9SNl3bV56G89ueJQnCX0B         zSk9U/YGeGwZ3VMt79K4gLzigep61zbcVu3eagOx9qBZltPkuBSaBRLrj93rJA7yj09/         iw/nQdy8TdlNFFSPzMj2NlzB/IL8FKjHfQyQyoNtOK2S1MyhriOV115VhBIoSzRwhDcx         L9QA=="),
                        new MessagePartHeader().setName("X-Google-DKIM-Signature").setValue("v=1; a=rsa-sha256; c=relaxed/relaxed;        d=1e100.net; s=20161025;        h=x-gm-message-state:mime-version:from:date:message-id:subject:to;        bh=xVesd8rXeQ4tlqf6bRlB3HAki1/L7uTmJ9M1mKyzLLQ=;        b=cSFGFanV8lQ7FNpfrA8WnZf+IHLJiH33Tp2KxFVOGPwdtJCem4dwPW0UH6BqLqMUZV         q3vJj+s2nFIIZV8HMhHNBGcDAUaTUX4TGyFmLb0V54t05oQrMtm2ARPLE4ri3q9wej9u         OrCnfWdFICNu/rmJacuTGUuzYdGRE02eYzH6+LqjMz1N3VosgglOZZ70UdjkPCA24aoS         iGkw12iyIkgTBxOXNvi1+FHpmz/tL2yEX6xOkoe4emqIl/gR5ZebucvW2L2+LwCyVt5h         +EoAyAh/GyQhh2/gDZJZWScSzYnzdGxLWeZAI2MSAnqiiJCMJnFf+6nSf64DNP8bjoQw         bj4g=="),
                        new MessagePartHeader().setName("X-Gm-Message-State").setValue("AIkVDXJfzZGIltVicEoVp3jOpdeMmIK+IeteJMgqXAwSURgB1inW8NzPJzC2FjCrMFeOkOUdklQuMTSEuHEzAA=="),
                        new MessagePartHeader().setName("X-Received").setValue("by 10.28.103.134 with SMTP id b128mr32497079wmc.54.1482945731842; Wed, 28 Dec 2016 09:22:11 -0800 (PST)"),
                        new MessagePartHeader().setName("MIME-Version").setValue("1.0"),
                        new MessagePartHeader().setName("From").setValue("Code Raguet <ignacio.code@gmail.com>"),
                        new MessagePartHeader().setName("Date").setValue("Wed, 28 Dec 2016 17:22:00 +0000"),
                        new MessagePartHeader().setName("Message-ID").setValue("<CALrYDoyE4F6RVMjTbE59nj2VdduZqMfo7Ryz8YqrU=M8APq2sA@mail.gmail.com>"),
                        new MessagePartHeader().setName("Subject").setValue("f"),
                        new MessagePartHeader().setName("To").setValue("fraser.quote@gmail.com"),
                        new MessagePartHeader().setName("Content-Type").setValue("multipart/alternative; boundary=001a114b2fae915c770544bb35e2")))
                .setMimeType("multipart/alternative")
                .setParts(Arrays.asList(
                        new MessagePart()
                                .setBody(new MessagePartBody()
                                        .setData("RWwgaW5maWVybm8gZXMgZWwgb2x2aWRvDQo=")
                                        .setSize(26))
                                .setFilename("")
                                .setHeaders(singletonList(new MessagePartHeader().setName("Content-Type").setValue("text/plain; charset=UTF-8")))
                                .setMimeType("text/plain")
                                .setPartId("0"),
                        new MessagePart()
                                .setBody(new MessagePartBody()
                                        .setData(FIRST_GMAIL_MESSAGE_HTML_DATA)
                                        .setSize(143))
                                .setFilename("")
                                .setHeaders(singletonList(new MessagePartHeader().setName("Content-Type").setValue("text/html; charset=UTF-8")))
                                .setMimeType("text/html")
                                .setPartId("1"))))
                .setSizeEstimate(3388)
                .setSnippet("El infierno es el olvido")
                .setThreadId("15946741e5510f9a");
        return gmailMessage;
    }
}
