package com.wareflow.buildify.util;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.util.stream.Collectors;

// 📍 GeoUtil: 카카오 로컬 API를 사용하여 주소를 위도/경도로 변환하는 유틸 클래스
// GeoUtil: Utility class that uses Kakao Local API to convert addresses to latitude and longitude
public class GeoUtil {
    private static final String REST_KEY;

    // 🔑 REST API 키 로드 (카카오 주소-좌표 변환용)
    // Loads Kakao REST API key from application-secret.properties for address-to-coordinate conversion.
    static {
        Properties properties = new Properties();
        try (InputStream input = GeoUtil.class.getClassLoader().getResourceAsStream("application-secret.properties")) {
            if (input != null) {
                properties.load(input);
                REST_KEY = properties.getProperty("kakao.rest.key");
            } else {
                throw new RuntimeException("application-secret.properties not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API key from application-secret.properties", e);
        }
    }

    /**
     * 📌 주소를 위도/경도로 변환하는 메서드
     * Converts a full address string into a latitude/longitude coordinate pair using Kakao's REST API.
     *
     * @param address 전체 주소 문자열 (e.g., "서울특별시 강남구 테헤란로 123")
     * @return double 배열 [위도, 경도] 또는 실패 시 null
     */
    public static double[] getLatLngFromAddress(String address) {
        try {
            String apiKey = REST_KEY;
            // 요청 URL 구성: 카카오 주소 검색 REST API
            String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + URLEncoder.encode(address, "UTF-8");

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            // HTTP 요청 헤더에 인증 키 추가
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);
            conn.setRequestMethod("GET");

            // 응답 JSON을 한 줄로 읽어들임
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = br.lines().collect(Collectors.joining());

            // JSON 파싱 및 위도/경도 추출
            JSONObject obj = new JSONObject(json);
            JSONArray documents = obj.getJSONArray("documents");

            if (documents.length() > 0) {
                JSONObject meta = documents.getJSONObject(0);
                double lng = meta.getDouble("x");
                double lat = meta.getDouble("y");
                // 위도(y), 경도(x) 순서로 반환
                return new double[]{lat, lng};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 실패 시 null 반환
        return null;
    }
}