package org.motometer.telegram.bot.core.dao;

import com.amazonaws.auth.AWSCredentials;

import org.immutables.value.Value;

@Value.Immutable
interface AWSCredentialsImpl extends AWSCredentials {
    @Override
    @Value.Parameter
    String getAWSAccessKeyId();

    @Override
    @Value.Parameter
    String getAWSSecretKey();
}
