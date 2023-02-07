package jp.fujino.SalaryDataManager.application.controller;

import jp.fujino.SalaryDataManager.application.resource.HttpResponseObject;
import jp.fujino.SalaryDataManager.domain.object.SalaryData;
import jp.fujino.SalaryDataManager.domain.service.SalaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public SalaryData getDataByKey(
            @PathVariable("month") String month,
            @PathVariable("paymentType") String paymentType
    ) {
        try {
            HttpResponseObject response = new HttpResponseObject();
            response.setHttpStatus("OK");
            response.setMessage();
        }
        return salaryDataService.findById(month, paymentType);
    }

    /** Find by Month **/
    @GetMapping(value = "/searchByMonth/{month}")
    public List<SalaryData> getDataByMonth(
            @PathVariable("month") String month
    ) {
        return salaryDataService.findByMonth(month);
    }

    /** Find by PaymentType **/
    @GetMapping(value = "/searchByPaymentType/{paymentType}")
    public List<SalaryData> getDataByPaymentType(
            @PathVariable("paymentType") String paymentType
    ) {
        return salaryDataService.findByPaymentType(paymentType);
    }

}
