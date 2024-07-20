package com.technologyos.role.common;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The effort to standardize rest API error reports  is support by ITEF
 * (Internet Engineering Task Force, open standard organization  that which develop and promotes voluntary internet standards)
 * in RFC 7807 which created a generalized error-handling schema composed by five parts.
 * 1- type — A URI identifier that categorizes the error
 * 2-title — A brief, human-readable message about the error
 * 3-code —  The unique error code
 * 4-detail — A human-readable explanation of the error
 * 5-instance — A URI that identifies the specific occurrence of the error
 * Standardized is optional but have advantage, it is use for facebook and twitter ie
 * https://graph.facebook.com/oauth/access_token?
 * https://api.twitter.com/1.1/statuses/update.json?include_entities=true
 */
public class StandardizedApiExceptionResponse {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, required = true,
        example = "/errors/authentication/not-authorized", name="type")
    private String type ="/errors/uncategorized";

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, required = true,
        example = "The user does not have authorization", name="title")
    private String title;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, required = false,
        example = "192", name="code")
    private String code;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, required = true,
        example = "The user does not have the propertly permissions to access the " +
            "resource, please contact with ass https://digitalthinking.biz/es/ada-enterprise-core#contactus", name="detail")
    private String detail;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, required = true,
        example = "/errors/authentication/not-authorized/01", name="instance")
    private String instance ="/errors/uncategorized/bank";

    public StandardizedApiExceptionResponse(String title, String code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return code;
    }

    public void setStatus(String status) {
        this.code = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
