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

/**
 * @author Angela GALEANO
 * Integration test class for testing Reservation endpoints.
 */
public class ReservationIntegration {

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
    public void givenPath_whenAccessedWithoutAuthentication_then401IsReceived() throws ClientProtocolException, IOException {
        // Construct a GET request without authentication
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-500");
        // Execute the request
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        // Verify response status code is 401 (UNAUTHORIZED)
        Assertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
    }

    /**
     * Tests accessing a reservation by ID with proper authentication.
     *
     * @throws ClientProtocolException If a protocol error occurs.
     * @throws IOException             If an IO error occurs.
     */
    @Test
    public void givenReservationByIdAccessed_whenReservationInfoRetrieved_then200IsReceived() throws ClientProtocolException, IOException {
        // Construct a GET request with authentication and specific reservation ID
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-500");
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        // Execute the request
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        // Verify response status code is 200 (OK)
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
        // Expected MIME type for JSON content
        String jsonMimeType = "application/json";
        // Construct a GET request with authentication (no 'Accept' header)
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation");
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        // Execute the request
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        // Get the actual MIME type of the response
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        // Verify the actual MIME type matches the expected JSON MIME type
        Assertions.assertThat(jsonMimeType).isEqualTo(mimeType);
    }

    /**
     * Tests retrieving an existing reservation and verifying its details.
     *
     * @throws ClientProtocolException If a protocol error occurs.
     * @throws IOException             If an IO error occurs.
     */
    @Test
    public void givenReservationExists_whenReservationIsRetrieved_thenRetrievedResourceIsCorrect() throws ClientProtocolException, IOException {
        // Construct a GET request for a specific reservation ID with authentication
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-3");
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        // Execute the request
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        // Extract JSON response content
        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        // Deserialize JSON to ReservationEntity object
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ReservationEntity reservationRetrieved = mapper.readValue(jsonFromResponse, ReservationEntity.class);
        // Verify specific details of the retrieved reservation
        Assertions.assertThat(reservationRetrieved.getReservationUser()).isEqualTo("TEST RESERVATION");
    }

}