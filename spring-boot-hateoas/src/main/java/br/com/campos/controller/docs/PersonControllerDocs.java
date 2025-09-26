package br.com.campos.controller.docs;

import br.com.campos.data.dto.v1.PersonDTO;
import br.com.campos.data.dto.v2.PersonDTOV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonControllerDocs {
   @Operation(
            summary = "Finds a person",
            description = "Finds a person by their Id.",
            tags = {"PersonDTO", "Person"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonDTO.class)),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = PersonDTO.class)),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE, schema = @Schema(implementation = PersonDTO.class))
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    PersonDTO findById(@Parameter(description = "The Id of the person to find.") @PathVariable("id") Long id);

    @Operation(
            summary = "Finds all people",
            description = "Finds all people.",
            tags = {"PersonDTO", "Person"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    List<PersonDTO> findAll();

    @Operation(
            summary = "Add a new person",
            description = "Add a persons by passing in a JSON, XML, YML representation of the person",
            tags = {"PersonDTO", "Person"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    PersonDTO create(@RequestBody PersonDTO person);

    @Operation(
            summary = "Add a new person new version=2.0",
            description = "Add a persons by passing in a JSON, XML, YML representation of the person in a new version=2.0",
            tags = {"PersonDTOV2", "Person"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    PersonDTOV2 create(@RequestBody PersonDTOV2 person);

    @Operation(
            summary = "Updates a person's information",
            description = "Updates a person´s information by passing in a JSON, XML or YML representation of the update",
            tags = {"PersonDTO", "Person"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    PersonDTO update(@RequestBody PersonDTO person);

    @Operation(
            summary = "Delete",
            description = "Delete a person from database",
            tags = {"PersonDTO", "Person"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204", content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}