package com.phamchien.payment.config;

public final class Config {

    private Config() {
    }

    // ==============================
    // VNPAY SANDBOX
    // ==============================

    public static final String VNP_PAY_URL =
            "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

    // GIỮ NGUYÊN TMN CODE CỦA BẠN
    public static final String VNP_TMN_CODE =
            "SLQPZH3M";

    // GIỮ NGUYÊN HASH SECRET CỦA BẠN
    public static final String VNP_HASH_SECRET =
            "MEXFJDZBNYMXMMRBCZZREYVKJTWZAETU";

    // Local: http://localhost:8080/sendmailC14
    // Render sẽ ghi đè bằng biến BASE_URL
    public static final String BASE_URL =
            envOrDefault(
                    "BASE_URL",
                    "http://localhost:8080/sendmailC14"
            );

    public static final String VNP_RETURN_URL =
            BASE_URL + "/vnpay-return";

    public static final String VNP_IPN_URL =
            BASE_URL + "/vnpay-ipn";


    // ==============================
    // DATABASE
    // ==============================

    // Local:
    // localhost\SQLEXPRESS
    //
    // Render:
    // sẽ lấy DB_URL từ Environment Variables

    public static final String DB_URL =
            envOrDefault(
                    "DB_URL",
                    "jdbc:sqlserver://localhost\\SQLEXPRESS;"
                            + "databaseName=PaymentDemo;"
                            + "encrypt=true;"
                            + "trustServerCertificate=true"
            );

    public static final String DB_USER =
            envOrDefault(
                    "DB_USER",
                    "sa"
            );

    public static final String DB_PASSWORD =
            envOrDefault(
                    "DB_PASSWORD",
                    ""
            );


    // ==============================
    // BREVO API
    // ==============================

    public static final String BREVO_API_URL =
            "https://api.brevo.com/v3/smtp/email";

    // Không ghi API Key trực tiếp vào code
    public static final String BREVO_API_KEY =
            envOrDefault(
                    "BREVO_API_KEY",
                    ""
            );

    public static final String BREVO_SENDER_NAME =
            "Thanh toán VNPay";

    // Email bạn đã Verify trên Brevo
    public static final String BREVO_SENDER_EMAIL =
            "hoanggiakiet1505@gmail.com";


    // ==============================
    // ENVIRONMENT VARIABLE
    // ==============================

    private static String envOrDefault(
            String key,
            String defaultValue) {

        String value =
                System.getenv(key);

        if (value == null
                || value.isBlank()) {

            return defaultValue;
        }

        return value;
    }
}