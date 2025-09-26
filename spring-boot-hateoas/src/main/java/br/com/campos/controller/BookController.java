package br.com.campos.controller;


import br.com.campos.data.dto.v1.BookDTO;
import br.com.campos.data.dto.v1.PersonDTO;
import br.com.campos.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Person", description = "Endpoints for managing person")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE
            }
    )
    @Operation(
            summary = "Finds all books",
            description = "Finds all books.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<BookDTO> findAll() {
        return bookServices.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    @Operation(
            summary = "Finds a book",
            description = "Finds a specific book with ID",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public BookDTO findById(@Parameter(description = "The Id of the book to find.") @PathVariable("id") Long id) {
        return bookServices.findById(id);
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )
    @Operation(
            summary = "Add a new book",
            description = "Add a new book information in the database",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public BookDTO create(@Parameter(description = "Create a book") @RequestBody BookDTO bookDTO) {
        return bookServices.create(bookDTO);
    }

    @PutMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )
    @Operation(
            summary = "Updates a books information",
            description = "Updates a books information by passing in a JSON, XML or YML representation of the update",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201",
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
    public BookDTO update(
            @Parameter(description = "ID do livro a ser atualizado") @PathVariable("id") Long id,
            @Parameter(description = "Dados do livro para atualização") @RequestBody BookDTO bookDTO) {
        return bookServices.update(id, bookDTO);
    }


    @DeleteMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    @Operation(
            summary = "Delete a book",
            description = "Delete a specific book with ID",
            tags = {"Books"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "204",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                                    @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))),
                            }
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> delete(@Parameter(description = "Delete book by id.") @PathVariable("id") Long id) {
        bookServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
