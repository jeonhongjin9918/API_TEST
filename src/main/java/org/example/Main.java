package org.example;

/*
import phis.nu.his.cmc.common.Role;
import phis.nu.his.li.li_liscomlib.liscom.LisComMgt;
import phis.nu.his.li.li_liscomlib.codemgt.LisComCodeMgt;
import phis.nu.his.li.li_liscomlib.plgycommgt.PlgyCommonMgt;
import phis.nu.his.li.lp_plgyrsltmngtmgr.testrsltrgstmgt.dao.TestRsltRgstDAO;
import phis.nu.his.pm.pm_pamlib.etcoutrgst.EtcOutRgst;
import phis.nu.his.sz.messagelib.instantmessage.InstantMessage;
import phis.nu.his.sz.shortmsglib.shortmsg.ShortMsgUtil;
import phis.nuframe.context.ContextAwareService;
import phis.nuframe.exception.UserException;
import phis.nuframe.util.StringHelper;
import phis.nuframe.vo.ValueObject;
import phis.nuframe.vo.ValueObjectAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

*/
import java.io.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/*
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import org.json.JSONObject;
import org.json.JSONArray;

import net.sf.json.JSONObject;
*/


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;


public class Main {



    // HTTP 연결을 생성하고 반환하는 함수 (Authorization 헤더를 추가하여 Bearer Token 전달)
    public static HttpURLConnection createHttpURLConnection(String urlString, String method, String contentType, String bearerToken) throws IOException {
        // URL 객체 생성
        URL url = new URL(urlString);

        // HTTP 연결 설정
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);  // 요청 메서드 설정 (POST, GET 등)
        connection.setRequestProperty("Content-Type", contentType);  // 요청 헤더 설정

        // Bearer Token이 제공되면 Authorization 헤더에 추가
        if (bearerToken != null && !bearerToken.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
        }

        if ("POST".equals(method)) {
            connection.setDoOutput(true);  // 출력 스트림 사용
        }

        connection.setDoInput(true);   // 입력 스트림 사용

        return connection;  // 설정된 connection 객체 반환
    }

    // HTTP 응답 본문을 읽는 함수
    public static String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    // HTTP 에러 응답 본문을 읽는 함수 (500 등 에러 응답)
    public static String readErrorResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    // 요청 본문을 전송하는 함수
    public static String sendRequest(String urlString, String method, String contentType, String bearerToken, String jsonInputString) {
        try {
            // HTTP 연결 생성
            HttpURLConnection connection = createHttpURLConnection(urlString, method, contentType, bearerToken);

            // 요청 본문에 데이터 작성
            if (jsonInputString != null) {
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            System.out.println("API Response Code: " + responseCode);

            // 응답 본문을 읽기 (500 에러일 경우 ErrorStream 사용)
            String responseBody;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                responseBody = readResponse(connection);
            } else {
                responseBody = readErrorResponse(connection);  // 500 에러 처리
            }

            // 연결 종료
            connection.disconnect();

            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        // 로그인 후 Bearer Token을 얻음
        String bearerToken ="1";
        //bearerToken = getSmlabLogin();


        if (bearerToken != null) {
            System.out.println("Bearer Token: " + bearerToken);



            //orderRegistrationBody(bearerToken); // 오더등록
            //viewOrdersQueryParams(bearerToken); // 전송된오더조회
            //cancelOrdersBody(bearerToken);      // 전송한 오더 취소
             //fetchResultsBody(bearerToken);      // 결과조회
            // updateResultStateBody(bearerToken); // 결과 상태 업데이트
            //cancelOrdersBody(bearerToken);      // 전송한 오더 취소

            // 데이터 1 (data1, data2, data3 등 동일 구조)
            final Map<String, Object> data1 = createData("2024-01-24", "1234567890", "3345455", "홍길동","GOT", "s-got", 1, "코멘트 입니다");
            final Map<String, Object> data2 = createData("2024-01-24", "1234567890", "3345455", "홍길동","GPT", "s-gpt", 2, "별다른 코멘트");
            final Map<String, Object> data3 = createData("2024-01-24", "1234567890", "3345455", "홍길동","ALP", "s-alp", 3, "");
            final Map<String, Object> data4 = createData("2024-01-24", "1234567890", "3345455", "김길동","GOT", "s-got", 1, "코멘트 입니다");
            final Map<String, Object> data5 = createData("2024-01-24", "1234567890", "3345455", "김길동","GPT", "s-gpt", 2, "별다른 코멘트");
            final Map<String, Object> data6 = createData("2024-01-24", "1234567890", "3345455", "박길동","ALP", "s-alp", 1, "");
            final Map<String, Object> data7 = createData("2024-01-24", "1234567890", "3345455", "최길동","ALP", "s-alp", 1, "");

            int cnt1 =0, cnt2=0 , cnt3=0 , cnt4=0 , cnt5=0 , cnt6 =0 ;

            // 3개의 데이터를 하나의 리스트에 담기
            List<Map<String, Object>> dataList = new ArrayList<>();
            dataList.add(data1);
            dataList.add(data2);
            dataList.add(data3);
            dataList.add(data4);
            dataList.add(data5);
            dataList.add(data6);
            dataList.add(data7);

            // 최종 구조 만들기
            Map<String, Object> result = new HashMap<>();


            // 환자 정보들 (각 환자 정보를 담을 리스트)
            List<Map<String, Object>> patientInfos = new ArrayList<>();

            // 환자 정보를 그룹화할 Map (orderDate, orderNo, chartNo, patientName으로 그룹화)
            Map<String, Map<String, Object>> patientGroups = new HashMap<>();

            // 환자 정보 그룹화

            for (Map<String, Object> test : dataList) {
                // 환자 정보의 key를 만들어서 그룹화 (orderDate + orderNo + chartNo + patientName)
                String key = test.get("orderDate") + "_" + test.get("orderNo") + "_" + test.get("chartNo") + "_" + test.get("patientName");

                // 만약 해당 그룹이 존재하지 않으면 새로 생성
                patientGroups.putIfAbsent(key, new HashMap<>());
                Map<String, Object> patientInfo = patientGroups.get(key);

                // 환자 기본 정보를 추가 (첫 번째 검사 정보를 기준으로 추가)
                if (patientInfo.isEmpty()) {
                    System.out.println();
                    System.out.println("patientInfo 생성 " + cnt1++ );
                    patientInfo.put("orderDate"         , test.get("orderDate")); // 병원 처방일자 (orderDate) (필수)
                    patientInfo.put("orderNo"           , test.get("orderNo")); // 병원 접수번호 (orderNo) (필수)
                    patientInfo.put("chartNo"           , test.get("chartNo")); // 차트번호 (chartNo)
                    patientInfo.put("patientName"       , test.get("patientName")); // 수진자명 (patientName) (필수)
                    patientInfo.put("birthDate"         , test.get("birthDate")); // 생년월일 (birthDate)
                    patientInfo.put("identificationNo"  , test.get("identificationNo")); // 주민등록번호 (identificationNo)
                    patientInfo.put("gender"            , test.get("gender")); // 성별 (gender)
                    patientInfo.put("age"               , test.get("age")); // 나이 (age)
                    patientInfo.put("dept"              , test.get("dept")); // 진료과 (dept)
                    patientInfo.put("ward"              , test.get("ward")); // 병동 (ward)
                    patientInfo.put("doctorName"        , test.get("doctorName")); // 진료의 (doctorName)
                    patientInfo.put("sampleDrawDateTime", test.get("sampleDrawDateTime")); // 검체채취일시 (sampleDrawDateTime)
                    patientInfo.put("testInfos", new ArrayList<Map<String, Object>>());
                    cnt2 = 0;
                }


                System.out.println("검사 정보 추가 " + cnt2++);
                // 검사 정보 추가
                List<Map<String, Object>> testInfos = (List<Map<String, Object>>) patientInfo.get("testInfos");
                Map<String, Object> testInfo = new HashMap<>();


                testInfo.put("sampleNo"      , test.get("sampleNo")); // 병원 검체 번호 (sampleNo)
                testInfo.put("testCode"      , test.get("testCode")); // 병원 검사 코드 (testCode) (필수)
                testInfo.put("testName"      , test.get("testName")); // 병원 검사명 (testName) (필수)
                testInfo.put("sampleCode"    , test.get("sampleCode")); // 병원 검체 코드 (sampleCode)
                testInfo.put("sampleName"    , test.get("sampleName")); // 병원 검체명 (sampleName)
                testInfo.put("comment"       , test.get("comment")); // 검사 코멘트 (comment)
                testInfo.put("sequence"      , test.get("sequence")); // 순번 (sequence) (값이 없으면 0)
                testInfo.put("orderDateTime" , test.get("orderDateTime")); // 오더 생성일시 (orderDateTime)


                testInfos.add(testInfo);

            }


            // 그룹화된 환자 정보를 patientInfos에 추가
            patientInfos.addAll(patientGroups.values());

            result.put("institutionNo", data1.get("institutionNo"));
            result.put("hospitalCode", data1.get("hospitalCode"));

            result.put("patientInfos", patientInfos);

            // JSON 문자열로 변환
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Pretty printing for better readability
            String jsonString = gson.toJson(result);
            System.out.println(jsonString);

        } else {
            System.out.println("로그인 실패. Bearer Token을 가져올 수 없습니다.");
        }
    }


    // 공통 데이터 생성 함수
    private static Map<String, Object> createData(String orderDate,String orderNo, String chartNo,String patientName, String testCode, String testName, int sequence, String comment) {
        Map<String, Object> data = new HashMap<>();
        // root
        data.put("institutionNo", "12345678");
        data.put("hospitalCode", "9999999");
        // patientInfos
        data.put("orderDate", orderDate);
        data.put("orderNo", orderNo);
        data.put("chartNo", chartNo);
        data.put("patientName", patientName);
        data.put("birthDate", "1985-01-01");
        data.put("identificationNo", "8501011");
        data.put("gender", "M");
        data.put("age", 38);
        data.put("dept", "DS");
        data.put("ward", "1병동");
        data.put("doctorName", "김의사");
        data.put("sampleDrawDateTime", "2024-01-24 15:15:40");
        // testInfos
        data.put("sampleNo", "1234522");
        data.put("testCode", testCode);  // 매개변수로 전달받은 검사 코드
        data.put("testName", testName);  // 매개변수로 전달받은 검사명
        data.put("sampleCode", "2");
        data.put("sampleName", "SST Serum");
        data.put("comment", comment);  // 매개변수로 전달받은 코멘트
        data.put("sequence", sequence);  // 매개변수로 전달받은 순번
        data.put("orderDateTime", "2024-01-24 15:11:25");
        return data;
    }


    public static String getSmlabLogin() {
        System.out.println("getSmlabLogin - 로그인 토큰발행");

        String urlString = "https://testinterface.smlab.co.kr/authentication/login";  // 로그인 API URL
        String id = "9999999";  // 사용자 ID
        String password = "QF6jNZfDypnOGANQ3ZryKWLl";  // 사용자 비밀번호

        // 로그인 요청 데이터 (JSON 형식)
        String jsonInputString = String.format("{\"id\":\"%s\", \"password\":\"%s\"}", id, password);

        // 로그인 API 호출
        String responseBody = sendRequest(urlString, "POST", "application/json", null, jsonInputString);

        // 응답에서 Bearer Token 추출
        return extractBearerToken(responseBody);
    }

    private static String extractBearerToken(String responseBody) {
        String token = null;
        int tokenStart = responseBody.indexOf("\"AccessToken\":\"") + 15;  // "AccessToken" 시작 위치
        int tokenEnd = responseBody.indexOf("\"", tokenStart);    // "AccessToken" 종료 위치

        if (tokenStart != -1 && tokenEnd != -1) {
            token = responseBody.substring(tokenStart, tokenEnd);
        } else {
            System.out.println("Bearer Token을 찾을 수 없습니다.");
        }

        return token;
    }

    // 오더등록
    public static void orderRegistrationBody(String bearerToken) {
        System.out.println("orderRegistrationBody - 오더등록");
        String urlString = "https://testinterface.smlab.co.kr/ocs/integrationInterface/orders";  // 다른 API URL

        // 요청 파라미터 (JSON 형식)
        /*
        String jsonInputString = "{" +
                "\"institutionNo\": \"12345678\"," +
                "\"hospitalCode\": \"9999999\"," +
                "\"patientInfos\": [" +
                "{" +
                "\"orderDate\": \"2024-01-24\"," +
                "\"orderNo\": \"1234567890\"," +
                "\"chartNo\": \"3345455\"," +
                "\"patientName\": \"홍길동\"," +
                "\"birthDate\": \"1985-01-01\"," +
                "\"identificationNo\": \"8501011\"," +
                "\"gender\": \"M\"," +
                "\"age\": 38," +
                "\"dept\": \"DS\"," +
                "\"ward\": \"1병동\"," +
                "\"doctorName\": \"김의사\"," +
                "\"sampleDrawDateTime\": \"2024-01-24 15:15:40\"," +
                "\"testInfos\": [" +
                "{" +
                "\"sampleNo\": \"1234522\"," +
                "\"testCode\": \"GOT\"," +
                "\"testName\": \"s-got\"," +
                "\"sampleCode\": \"02\"," +
                "\"sampleName\": \"SST Serum\"," +
                "\"comment\": \"코멘트 입니다\"," +
                "\"sequence\": 1," +
                "\"orderDateTime\": \"2024-01-24 15:11:25.120\"" +
                "}," +
                "{" +
                "\"sampleNo\": \"1234522\"," +
                "\"testCode\": \"GPT\"," +
                "\"testName\": \"s-gpt\"," +
                "\"sampleCode\": \"02\"," +
                "\"sampleName\": \"SST Serum\"," +
                "\"comment\": \"\"," +
                "\"sequence\": 2," +
                "\"orderDateTime\": \"2024-01-24 15:11:25.122\"" +
                "}" +
                "]" +
                "}" +
                "]" +
                "}";
         */

        String jsonInputString = "{"
                + "\"patientInfos\": [{"
                + "    \"patientName\": \"홍길동\","
                + "    \"orderNo\": \"1234567890\","
                + "    \"testInfos\": [{"
                + "        \"sampleName\": \"SST Serum\","
                + "        \"orderDateTime\": \"2024-01-23 15:11:25.120\","
                + "        \"sequence\": 1,"
                + "        \"testCode\": \"GOT\","
                + "        \"sampleCode\": \"02\","
                + "        \"comment\": \"코멘트 입니다\","
                + "        \"sampleNo\": \"1234522\","
                + "        \"testName\": \"s-got\""
                + "    }, {"
                + "        \"sampleName\": \"SST Serum\","
                + "        \"orderDateTime\": \"2024-01-23 15:11:25.122\","
                + "        \"sequence\": 2,"
                + "        \"testCode\": \"GPT\","
                + "        \"sampleCode\": \"02\","
                + "        \"comment\": \"\","
                + "        \"sampleNo\": \"1234522\","
                + "        \"testName\": \"s-gpt\""
                + "    }],"
                + "    \"gender\": \"M\","
                + "    \"dept\": \"DS\","
                + "    \"ward\": \"1병동\","
                + "    \"sampleDrawDateTime\": \"2024-01-23 15:15:40\","
                + "    \"birthDate\": \"1985-01-01\","
                + "    \"chartNo\": \"3345455\","
                + "    \"doctorName\": \"김의사\","
                + "    \"identificationNo\": \"8501011\","
                + "    \"orderDate\": \"2024-01-23\","
                + "    \"age\": 38"
                + "}],"
                + "\"institutionNo\": \"12345678\","
                + "\"hospitalCode\": \"9999999\""
                + "}";


        // 오더 등록 API 호출
        String responseBody = sendRequest(urlString, "POST", "application/json", bearerToken, jsonInputString);
        System.out.println("API Response: " + responseBody);
    }

    // 전송된 오더 확인
    public static void viewOrdersQueryParams(String bearerToken) {
        System.out.println("viewOrdersQueryParams - 전송된 오더 확인");
        String urlString = "https://testinterface.smlab.co.kr/ocs/integrationInterface/orders";  // 다른 API URL

        // 쿼리 파라미터로 URL을 수정
        String urlWithParams = urlString + "?hospitalCode=9999999&beginDate=2024-01-01&endDate=2024-03-30";

        // 전송된 오더 조회 API 호출
        String responseBody = sendRequest(urlWithParams, "GET", "application/json", bearerToken, null);
        System.out.println("API Response: " + responseBody);
    }

    // 결과조회
    public static void fetchResultsBody(String bearerToken) throws ParseException {
        System.out.println("fetchResultsBody - 결과조회");

        String urlString = "https://testinterface.smlab.co.kr/ocs/integrationInterface/results";  // 다른 API URL

        JSONObject jparam = new JSONObject();
        jparam.put("hospitalCode", "9999999");
        jparam.put("resultBeginDate", "2024-01-01");
        jparam.put("resultEndDate", "2024-01-30");
        jparam.put("resultState", "A");

        String jsonInputString = jparam.toJSONString();

        // 결과 조회 API 호출
        String responseBody = sendRequest(urlString, "POST", "application/json", bearerToken, jsonInputString);
        System.out.println("API Response: " + responseBody);

        JSONParser parser = new JSONParser();
        // JSON 문자열을 파싱하여 JSONObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(responseBody);

        // JSON 객체에서 값 가져오기
        String hospitalCode = (String) jsonObject.get("hospitalCode");  // get()을 사용하고 형변환
        String hospitalName = (String) jsonObject.get("hospitalName");

        // 환자 정보 추출
        JSONArray patientInfos = (JSONArray) jsonObject.get("patientInfos");

        // 환자 정보 반복문을 사용하여 처리
        for (Object patientObj : patientInfos) {
            JSONObject patientInfo = (JSONObject) patientObj;  // 각 환자 정보 JSONObject로 변환
            String patientName = (String) patientInfo.get("patientName");
            long age = (Long) patientInfo.get("age");  // age는 JSON에서 숫자이므로 long으로 가져옵니다.
            String registDate = (String) patientInfo.get("registDate");
            String registNo = (String) patientInfo.get("registNo");

            // 각 환자 정보 출력
            System.out.println("Patient Name: " + patientName);
            System.out.println("Age: " + age);
            System.out.println("Regist Date: " + registDate);
            System.out.println("Regist No: " + registNo);
            System.out.println("------------");
        }




        /*

        // 1. Gson 2.8.1에서 사용되는 JsonParser.parse()를 사용하여 JSON 문자열을 JsonElement로 파싱
        JsonParser parser = new JsonParser(); // 2.8.1 버전에서는 객체 생성 후 사용해야 함
        JsonElement jsonElement = parser.parse(responseBody); // parse() 호출

        // 2. JSON을 XML로 변환
        String xmlString = jsonToXml(jsonElement);

        // 3. 결과 출력
        System.out.println("xmlString : " + xmlString);

         */
    }

    // 결과 상태 업데이트
    public static void updateResultStateBody(String bearerToken) {
        System.out.println("updateResultStateBody - 결과 상태 업데이트");

        String urlString = "https://testinterface.smlab.co.kr/ocs/integrationInterface/resultstate";  // 다른 API URL

        // 요청 파라미터 (JSON 형식)
        String jsonInputString = "{" +
                "\"hospitalCode\": \"9999999\"," +
                "\"testInfos\": [" +
                "{" +
                "\"smlRegistDate\": \"2024-01-23\"," +
                "\"smlRegistNo\": \"88001\"," +
                "\"smlReportDate\": \"2024-01-26\"," +
                "\"smlTestCode\": \"11001\"," +
                "\"smlTestSubCode\": \"\"" +
                "}" +
                "]" +
                "}";

        // 결과 상태 업데이트 API 호출
        String responseBody = sendRequest(urlString, "POST", "application/json", bearerToken, jsonInputString);
        System.out.println("API Response: " + responseBody);


    }

    // 전송한 오더 취소
    public static void cancelOrdersBody(String bearerToken) {
        System.out.println("cancelOrdersBody - 전송한 오더 취소");

        String urlString = "https://testinterface.smlab.co.kr/ocs/integrationInterface/cancelorders";  // 다른 API URL

        // 요청 파라미터 (JSON 형식)
        var jsonInputString = "{" +
                "\"hospitalCode\": \"9999999\"," +
                "\"cancelOrders\": [" +
                "{" +
                "\"orderDate\": \"2024-01-23\"," +
                "\"orderNo\": \"1234567890\"," +
                "\"chartNo\": \"3345455\"," +
                "\"patientName\": \"홍길동\"," +
                "\"testCode\": \"GOT\"" +
                "}," +
                "{" +
                "\"orderDate\": \"2024-01-23\"," +
                "\"orderNo\": \"1234567890\"," +
                "\"chartNo\": \"3345455\"," +
                "\"patientName\": \"홍길동\"," +
                "\"testCode\": \"GPT\"" +
                "}" +
                "]" +
                "}";

        // 오더 취소 API 호출
        String responseBody = sendRequest(urlString, "POST", "application/json", bearerToken, jsonInputString);
        System.out.println("API Response: " + responseBody);
    }

    // JsonElement를 XML 형식의 문자열로 변환하는 메서드
    public static String jsonToXml(JsonElement jsonElement) {
        StringBuilder xmlBuilder = new StringBuilder();

        // JSON 객체인 경우
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // 각 필드(속성) 순회
            for (String key : jsonObject.keySet()) {
                JsonElement value = jsonObject.get(key);

                // XML 태그 추가
                xmlBuilder.append("<" + key + ">");

                if (value.isJsonObject() || value.isJsonArray()) {
                    // 객체나 배열일 경우 재귀적으로 XML 변환
                    xmlBuilder.append(jsonToXml(value));  // 재귀 호출
                } else {
                    // 값인 경우 그 값을 바로 추가 (특수문자 처리)
                    xmlBuilder.append(escapeXml(value.getAsString()));
                }

                xmlBuilder.append("</" + key + ">\n");
            }
        }

        // JSON 배열인 경우
        else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            // 배열의 각 요소를 순회
            for (JsonElement arrayElement : jsonArray) {
                // 배열 요소도 재귀적으로 XML 변환
                xmlBuilder.append("<item>");
                xmlBuilder.append(jsonToXml(arrayElement));  // 재귀 호출
                xmlBuilder.append("</item>\n");
            }
        }

        return xmlBuilder.toString();
    }

    // 특수문자들을 XML 엔티티로 변환 (이스케이프 처리)
    public static String escapeXml(String value) {
        if (value == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);

            // 특수문자들을 XML 엔티티로 바꿔줍니다.
            switch (c) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '\'':
                    result.append("&apos;");
                    break;
                case '\"':
                    result.append("&quot;");
                    break;
                default:
                    // 한글과 다른 유니코드 문자들은 UTF-8로 자동 처리됩니다.
                    result.append(c);
                    break;
            }
        }

        return result.toString();
    }


}
