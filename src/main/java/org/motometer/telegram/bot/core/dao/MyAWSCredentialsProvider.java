package org.motometer.telegram.bot.core.dao;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MyAWSCredentialsProvider implements AWSCredentialsProvider {

    private final AWSCredentials credentials;

    @Override
    public AWSCredentials getCredentials() {
        return credentials;
    }

    @Override
    public void refresh() {

    }
}
