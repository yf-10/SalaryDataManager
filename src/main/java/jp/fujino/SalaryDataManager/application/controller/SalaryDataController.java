package jp.fujino.SalaryDataManager.application.controller;

import jp.fujino.SalaryDataManager.application.resource.HttpResponseObject;
import jp.fujino.SalaryDataManager.application.resource.SalaryDataAdd;
import jp.fujino.SalaryDataManager.domain.object.Money;
import jp.fujino.SalaryDataManager.domain.object.SalaryData;
import jp.fujino.SalaryDataManager.domain.service.SalaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/salary",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SalaryDataController {

    @Autowired
    private SalaryDataService salaryDataService;

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    @GetMapping(value = "/findById")
    public HttpResponseObject findById(
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
    @GetMapping(value = "/findByMonth")
    public HttpResponseObject findByMonth(
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

    /** Find by Month Between **/
    @GetMapping(value = "/findByMonthBetween")
    public HttpResponseObject findByMonthBetween(
            @RequestParam("from") final String from,
            @RequestParam("to") final String to
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.findByMonthBetween(from, to));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Find by Month After **/
    @GetMapping(value = "/findByMonthAfter")
    public HttpResponseObject findByMonthAfter(
            @RequestParam("month") final String month
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.findByMonthAfter(month));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Find by Month Before **/
    @GetMapping(value = "/findByMonthBefore")
    public HttpResponseObject findByMonthBefore(
            @RequestParam("month") final String month
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.findByMonthBefore(month));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Find by PaymentType **/
    @GetMapping(value = "/findByPaymentType")
    public HttpResponseObject findByPaymentType(
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

    /** Find by PaymentType In **/
    @GetMapping(value = "/findByPaymentTypeIn")
    public HttpResponseObject findByPaymentTypeIn(
            @RequestBody final List<String> paymentType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.findByPaymentTypeIn(paymentType));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * CRUD: Create
     */

    /** Add **/
    @PostMapping(value = "/add")
    public HttpResponseObject add(
            @RequestParam(value = "createdBy", defaultValue = "Anonymous") final String createdBy,
            @RequestParam(value = "month") final String month,
            @RequestParam(value = "paymentType") final String paymentType,
            @RequestParam(value = "amount") final int amount,
            @RequestParam(value = "currencyCode") final String currencyCode
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.add(
                    createdBy,
                    new SalaryDataAdd(month, paymentType, new Money(amount, currencyCode))
            ));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Add JSON **/
    @PostMapping(value = "/addJson")
    public HttpResponseObject addJson(
            @RequestParam(value = "createdBy", defaultValue = "Anonymous") final String createdBy,
            @RequestBody final SalaryDataAdd data
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.add(
                    createdBy,
                    data
            ));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Add List **/
    @PostMapping(value = "/addList")
    public HttpResponseObject addList(
            @RequestParam(value = "createdBy", defaultValue = "Anonymous") final String createdBy,
            @RequestBody final List<SalaryDataAdd> dataList
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.addList(
                    createdBy,
                    dataList
            ));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * CRUD: Update
     */

    /** Save **/
    @PostMapping(value = "/save")
    public HttpResponseObject save(
            @RequestParam(value = "updatedBy", defaultValue = "Anonymous") final String updatedBy,
            @RequestBody final SalaryData data
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.save(updatedBy, data));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * CRUD: Delete
     */

    /** Delete By PrimaryKey **/
    @PostMapping(value = "/deleteById")
    public HttpResponseObject save(
            @RequestParam(value = "month") final String month,
            @RequestParam(value = "paymentType") final String paymentType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDataService.deleteById(month, paymentType));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
