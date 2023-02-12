package jp.fujino.SalaryDataManager.application.controller;

import jp.fujino.SalaryDataManager.application.resource.HttpResponseObject;
import jp.fujino.SalaryDataManager.domain.object.Money;
import jp.fujino.SalaryDataManager.domain.object.SalaryData;
import jp.fujino.SalaryDataManager.domain.service.SalaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;

@RestController
@RequestMapping(
        value = "/salary",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SalaryDataController {

    @Autowired
    private SalaryDataService salaryDataService;

    /** Find by Primary key **/
    @GetMapping(value = "/searchByKey/{month}/{paymentType}")
    public HttpResponseObject getDataByKey(
            @PathVariable("month") String month,
            @PathVariable("paymentType") String paymentType
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
    @GetMapping(value = "/searchByMonth/{month}")
    public HttpResponseObject getDataByMonth(
            @PathVariable("month") String month
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
    @GetMapping(value = "/searchByPaymentType/{paymentType}")
    public HttpResponseObject getDataByPaymentType(
            @PathVariable("paymentType") String paymentType
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
            @RequestParam(value = "createdBy", defaultValue = "Unknown") String createdBy,
            @RequestParam(value = "month") String month,
            @RequestParam(value = "paymentType") String paymentType,
            @RequestParam(value = "amount") int amount,
            @RequestParam(value = "currencyCode") String currencyCode
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.addSalaryData(
                    createdBy,
                    month,
                    paymentType,
                    new Money(amount, Currency.getInstance(currencyCode))
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
            @RequestParam(value = "updatedBy", defaultValue = "Unknown") String updatedBy,
            @RequestBody SalaryData data
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
