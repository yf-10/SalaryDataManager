package jp.fujino.SalaryDataManager.application.controller;

import jp.fujino.SalaryDataManager.application.resource.HttpResponseObject;
import jp.fujino.SalaryDataManager.domain.object.Money;
import jp.fujino.SalaryDataManager.domain.object.SalaryData;
import jp.fujino.SalaryDataManager.domain.service.SalaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/salary",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SalaryDataController {

    @Autowired
    private SalaryDataService salaryDataService;

    /** Find by Primary key **/
    @GetMapping(value = "/searchByKey")
    public HttpResponseObject getDataByPrimaryKey(
            @RequestParam("month") final String month,
            @RequestParam("paymentType") final String paymentType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.findById(month, paymentType));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Find by Month **/
    @GetMapping(value = "/searchByMonth")
    public HttpResponseObject getDataByMonth(
            @RequestParam("month") final String month
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.findByMonth(month));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Find by PaymentType **/
    @GetMapping(value = "/searchByPaymentType")
    public HttpResponseObject getDataByPaymentType(
            @RequestParam("paymentType") final String paymentType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.findByPaymentType(paymentType));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Add Data **/
    @PostMapping(value = "/addData")
    public HttpResponseObject addData(
            @RequestParam(value = "createdBy", defaultValue = "Unknown") final String createdBy,
            @RequestParam(value = "month") final String month,
            @RequestParam(value = "paymentType") final String paymentType,
            @RequestParam(value = "amount") final int amount,
            @RequestParam(value = "currencyCode") final String currencyCode
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.addSalaryData(
                    createdBy,
                    month,
                    paymentType,
                    new Money(amount, currencyCode)
            ));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Save Data **/
    @PostMapping(value = "/saveData")
    public HttpResponseObject saveData(
            @RequestParam(value = "updatedBy", defaultValue = "Unknown") final String updatedBy,
            @RequestBody final SalaryData data
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.saveSalaryData(data, updatedBy));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
