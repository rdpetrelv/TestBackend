package com.laundry.laundryMgmt.ReservationTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundry.laundryMgmt.models.ReservationEntity;
import com.laundry.laundryMgmt.models.SensorEntity;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReservationIntegration {

    final String auth = "user" + ":" + "myPassword";
    final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
    final String authHeader = "Basic " + new String(encodedAuth);

    @Test
    public void givenPath_whenAccessedWithoutAuthentication_then401IsReceived() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-500");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void givenReservationByIdAccessed_whenReservationInfoRetrieved_then200IsReceived() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-500");

        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws ClientProtocolException, IOException {
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/reservation" );
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assertions.assertThat(jsonMimeType).isEqualTo( mimeType );
    }

    @Test
    public void givenReservationExists_whenReservationIsRetrieved_thenRetrievedResourceIsCorrect() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-3");
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ReservationEntity reservationRetrieved = mapper.readValue(jsonFromResponse, ReservationEntity.class);

        Assertions.assertThat(reservationRetrieved.getReservationUser()).isEqualTo("TEST RESERVATION");
    }


}