package ru.itis.javawarrior.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author lnurullina
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    //    "remoteAddress": "0:0:0:0:0:0:0:1",
//            "sessionId": "4CCB8D363F36DB1032F240C107C19927",
//            "tokenValue": "ya29.Gl_8BTv9NAUiiB69VboilxWfrEh1TQCTomsVAGgMVLUMq3avrsKsJ41XSqiqemOr-x32KLsdXs6CiZHRBtTpZzAWiSlQoPB2Oj8BSlzKTVpD5s9p9Ac3WqSpMizrMTo0jw",
//            "tokenType": "Bearer",
//            "decodedDetails": null
    private String remoteAddress;
    private String sessionId;
    private String tokenValue;
    private String tokenType;
    private String decodedDetails;
}
