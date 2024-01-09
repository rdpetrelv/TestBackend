package com.laundry.laundryMgmt.SensorTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundry.laundryMgmt.mappers.SensorMapper;
import com.laundry.laundryMgmt.models.SensorEntity;
import com.laundry.laundryMgmt.records.SensorRecord;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
/**
 * @author Fatima GHAOUI
 * Integration test class for testing Sensor endpoints.
 */
public class SensorIntegration {

    // Basic Authentication credentials
    final String auth = "user" + ":" + "myPassword";
    final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
    final String authHeader = "Basic " + new String(encodedAuth);
    /**
     * Tests accessing a path without authentication.
     *
     * @throws ClientProtocolException If a protocol error occurs.
     * @throws IOException             If an IO error occurs.
     */
    @Test
    public void givenPath_whenAccesedWithoutAuthentication_then401IsReceived() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/sensors/by-id/-500");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
    }
    /**
     * Tests accessing a sensor by ID with proper authentication.
     *
     * @throws ClientProtocolException If a protocol error occurs.
     * @throws IOException             If an IO error occurs.
     */
    @Test
    public void givenSensorByIdAcceses_whenSensorInfoRetrieved_then200IsReceived() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/sensors/by-id/-500");

        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
    /**
     * Tests a request without an 'Accept' header to verify default response content type.
     *
     * @throws ClientProtocolException If a protocol error occurs.
     * @throws IOException             If an IO error occurs.
     */
    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws ClientProtocolException, IOException {
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/sensors" );
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assertions.assertThat(jsonMimeType).isEqualTo( mimeType );
    }
    /**
     * Tests retrieving an existing sensor and verifying its details.
     *
     * @throws ClientProtocolException If a protocol error occurs.
     * @throws IOException             If an IO error occurs.
     */
    @Test
    public void givenSensorExists_whenSensorIsRetrieved_thenRetrievedResourceIsCorrect() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/sensors/by-id/-1");
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SensorEntity sensorRetrieved = mapper.readValue(jsonFromResponse, SensorEntity.class);

        Assertions.assertThat(sensorRetrieved.getSensorName()).isEqualTo("TEST SENSOR");
    }
}
