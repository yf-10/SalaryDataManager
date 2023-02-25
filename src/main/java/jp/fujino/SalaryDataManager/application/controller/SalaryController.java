package jp.fujino.SalaryDataManager.application.controller;

import jp.fujino.SalaryDataManager.application.resource.HttpResponseObject;
import jp.fujino.SalaryDataManager.domain.object.Salary;
import jp.fujino.SalaryDataManager.application.resource.SalaryModel;
import jp.fujino.SalaryDataManager.domain.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(
        value = "/salary",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    /**
     * CRUD: Read
     */

    /** GET by PrimaryKey **/
    @GetMapping(value = "/getById")
    public HttpResponseObject getById(
            @RequestBody final SalaryModel inputModel
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Convert to Object */
            final Salary inputObject = inputModel.modelToObjectGet();
            /* Output Object */
            final Salary outputObject = salaryService.getById(inputObject);
            /* Convert to Model */
            final SalaryModel outputModel = SalaryModel.convertToModel(outputObject);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModel);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** GET by Month **/
    @GetMapping(value = "/getByMonth")
    public HttpResponseObject getByMonth(
            @RequestParam("month") final String month
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Output Object */
            final List<Salary> outputObjects = salaryService.getByMonth(month);
            /* Convert to Model */
            final List<SalaryModel> outputModels = SalaryModel.convertToModels(outputObjects);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModels);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** GET by Month Between **/
    @GetMapping(value = "/getByMonthBetween")
    public HttpResponseObject getByMonthBetween(
            @RequestParam(value = "monthFrom") final String monthFrom,
            @RequestParam("monthTo") final String monthTo
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Output Object */
            final List<Salary> outputObjects = salaryService.getByMonthBetween(monthFrom, monthTo);
            /* Convert to Model */
            final List<SalaryModel> outputModels = SalaryModel.convertToModels(outputObjects);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModels);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** GET by Month After **/
    @GetMapping(value = "/getByMonthAfter")
    public HttpResponseObject getByMonthAfter(
            @RequestParam("monthFrom") final String monthFrom
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Output Object */
            final List<Salary> outputObjects = salaryService.getByMonthAfter(monthFrom);
            /* Convert to Model */
            final List<SalaryModel> outputModels = SalaryModel.convertToModels(outputObjects);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModels);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** GET by Month Before **/
    @GetMapping(value = "/getByMonthBefore")
    public HttpResponseObject getByMonthBefore(
            @RequestParam("monthTo") final String monthTo
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Output Object */
            final List<Salary> outputObjects = salaryService.getByMonthBefore(monthTo);
            /* Convert to Model */
            final List<SalaryModel> outputModels = SalaryModel.convertToModels(outputObjects);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModels);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** GET by PaymentItem **/
    @GetMapping(value = "/getByPaymentItem")
    public HttpResponseObject getByPaymentItem(
            @RequestParam("deduction") final Boolean deduction,
            @RequestParam("paymentItem") final String paymentItem
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Output Object */
            final List<Salary> outputObjects = salaryService.getByPaymentItem(deduction, paymentItem);
            /* Convert to Model */
            final List<SalaryModel> outputModels = SalaryModel.convertToModels(outputObjects);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModels);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** GET by PaymentItem In **/
    @GetMapping(value = "/getByPaymentItemIn")
    public HttpResponseObject findByPaymentItem(
            @RequestParam("deduction") final Boolean deduction,
            @RequestBody final List<String> paymentItemList
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Output Object */
            final List<Salary> outputObjects = salaryService.getByPaymentItem(deduction, paymentItemList);
            /* Convert to Model */
            final List<SalaryModel> outputModels = SalaryModel.convertToModels(outputObjects);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModels);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * CRUD: Create
     */

    /** POST **/
    @PostMapping(value = "/post")
    public HttpResponseObject post(
            @RequestBody final SalaryModel inputModel,
            @RequestParam(value = "createdBy", defaultValue = "Anonymous") final String createdBy,
            @RequestParam(value = "force", defaultValue = "false") final boolean force
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Convert to Object */
            final Salary inputObject = inputModel.modelToObjectPost(createdBy);
            /* Output Object */
            final Salary outputObject = salaryService.post(inputObject, force);
            /* Convert to Model */
            final SalaryModel outputModel = SalaryModel.convertToModel(outputObject);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModel);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /** POST All **/
    @PostMapping(value = "/postAll")
    public HttpResponseObject post(
            @RequestBody final List<SalaryModel> inputModels,
            @RequestParam(value = "createdBy", defaultValue = "Anonymous") final String createdBy,
            @RequestParam(value = "force", defaultValue = "false") final boolean force
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Convert to Object */
            List<Salary> inputObjects = new ArrayList<>();
            inputModels.forEach(each -> inputObjects.add(each.modelToObjectPost(createdBy)));
            /* Output Object */
            final List<Salary> outputObjects = salaryService.post(inputObjects, force);
            /* Convert to Model */
            final List<SalaryModel> outputModels = SalaryModel.convertToModels(outputObjects);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModels);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * CRUD: Update
     */

    /** PUT **/
    @PutMapping(value = "/put")
    public HttpResponseObject put(
            @RequestBody final SalaryModel inputModel,
            @RequestParam(value = "updatedBy", defaultValue = "Anonymous") final String updatedBy
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Convert to Object */
            final Salary inputObject = inputModel.modelToObjectPut(updatedBy);
            /* Output Object */
            final Salary outputObject = salaryService.put(inputObject);
            /* Convert to Model */
            final SalaryModel outputModel = SalaryModel.convertToModel(outputObject);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModel);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * CRUD: Delete
     */

    /** Delete By PrimaryKey **/
    @DeleteMapping(value = "/delete")
    public HttpResponseObject delete(
            @RequestBody final SalaryModel inputModel
    ) {
        HttpResponseObject response = new HttpResponseObject();
        try {
            /* Convert to Object */
            final Salary inputObject = inputModel.modelToObjectDelete();
            /* Output Object */
            final Salary outputObject = salaryService.delete(inputObject);
            /* Convert to Model */
            final SalaryModel outputModel = SalaryModel.convertToModel(outputObject);
            /* Set response */
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setResponseData(outputModel);
        } catch (Exception e) {
            /* Set response */
            response.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
