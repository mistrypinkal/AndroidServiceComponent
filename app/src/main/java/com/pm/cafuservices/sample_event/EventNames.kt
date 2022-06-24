package com.pm.cafuservices.sample_event

interface EventNames {
    interface EventName {
        companion object {
            const val PHONE_ENTERED = "phone_entered"

        }
    }

    interface ParamName {
        companion object {
            const val USER_STATUS = "user_status"
            const val CARRIER = "carrier"
            const val EMAIL_ADDRESS = "email_address"
            const val PHONE_NUMBER = "phone_number"
            const val COUNTRY_CODE = "country_code"

        }
    }

    interface ParamValues {
        companion object {
            const val NEW_USER = "new_user"
            const val RETURNING_USER = "returning_user"
            const val FORGOT_PASSWORD = "forgot_password"
            const val ETISALAT = "Etisalat"
            const val DU = "Du"
            const val VIRGIN_MOBILE = "Virgin"

        }
    }

    interface Properties {
        companion object {
            const val SIGNUP_DATE = "signup_date"
            const val REVIEW_CARWASH_TERMS = "review_carwash_terms"

        }
    }
}