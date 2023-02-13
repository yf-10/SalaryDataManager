package jp.fujino.SalaryDataManager.application.controller;

import jp.fujino.SalaryDataManager.application.resource.HttpResponseObject;
import jp.fujino.SalaryDataManager.application.resource.SalaryDeductionInput;
import jp.fujino.SalaryDataManager.domain.object.Money;
import jp.fujino.SalaryDataManager.domain.object.SalaryDeduction;
import jp.fujino.SalaryDataManager.domain.service.SalaryDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/salary_deduction",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SalaryDeductionController {

    @Autowired
    private SalaryDeductionService salaryDeductionService;

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    @GetMapping(value = "/findById")
    public HttpResponseObject findById(
            @RequestParam("month") final String month,
            @RequestParam("deductionType") final String deductionType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.findById(month, deductionType));
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
            response.setResponseData(salaryDeductionService.findByMonth(month));
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
            response.setResponseData(salaryDeductionService.findByMonthBetween(from, to));
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
            response.setResponseData(salaryDeductionService.findByMonthAfter(month));
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
            response.setResponseData(salaryDeductionService.findByMonthBefore(month));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Find by DeductionType **/
    @GetMapping(value = "/findByDeductionType")
    public HttpResponseObject findByDeductionType(
            @RequestParam("deductionType") final String deductionType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.findByDeductionType(deductionType));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Find by DeductionType In **/
    @GetMapping(value = "/findByDeductionTypeIn")
    public HttpResponseObject findByDeductionTypeIn(
            @RequestBody final List<String> deductionType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.findByDeductionTypeIn(deductionType));
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
            @RequestParam(value = "deductionType") final String deductionType,
            @RequestParam(value = "amount") final int amount,
            @RequestParam(value = "currencyCode") final String currencyCode
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.add(
                    createdBy,
                    new SalaryDeductionInput(month, deductionType, new Money(amount, currencyCode))
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
            @RequestBody final SalaryDeductionInput data
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.add(
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
            @RequestBody final List<SalaryDeductionInput> dataList
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.addList(
                    createdBy,
                    dataList
            ));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** Add List (Force) **/
    @PostMapping(value = "/addListForce")
    public HttpResponseObject addListForce(
            @RequestParam(value = "createdBy", defaultValue = "Anonymous") final String createdBy,
            @RequestBody final List<SalaryDeductionInput> dataList
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.addListForce(
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
            @RequestBody final SalaryDeduction data
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.save(updatedBy, data));
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
    public HttpResponseObject deleteById(
            @RequestParam(value = "month") final String month,
            @RequestParam(value = "deductionType") final String deductionType
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(salaryDeductionService.deleteById(month, deductionType));
        } catch (Exception e) {
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
