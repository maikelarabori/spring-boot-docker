package org.example.user.controller;

import org.example.user.model.User;
import org.example.user.service.UserQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@Execution(SAME_THREAD)
@DisplayName("UserQueryControllerTest: integration tests + doc")
class UserQueryingApiIntegrationTest {

  private MockMvc mockMvc;
  private RestDocumentationResultHandler documentationHandler;

  @MockBean
  UserQuery userQuery;

  @BeforeEach
  void setUp(final WebApplicationContext webApplicationContext,
             final RestDocumentationContextProvider restDocumentation) {
    this.documentationHandler = document("{method-name}",
        preprocessResponse(prettyPrint()));

    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(this.documentationHandler)
        .build();
  }

  @Test
  @DisplayName("Testing GET /api/users with success")
  public void getAllUsers() throws Exception {
    // Given
    final Iterable<User> iterableUsersStub = StubProvider.users();

    // When
    when(userQuery.getAll()).thenReturn(iterableUsersStub);

    // Then
    this.mockMvc.perform(get("/api/users")).andExpect(status().isFound())
        .andDo(this.documentationHandler.document(
            responseFields(
                subsectionWithPath("[]").description("The collection of users.").type(ARRAY),
                fieldWithPath("[].id").description("The user id.").type(STRING),
                fieldWithPath("[].username").description("The username.").type(STRING),
                fieldWithPath("[].status")
                    .description("The user status.").type(STRING),
                fieldWithPath("[].yearOfBirth")
                    .description("The user's year of birth.").type(NUMBER),
                fieldWithPath("[].deleted")
                    .description("Flag that tells if a user is soft deleted.").type(BOOLEAN),
                fieldWithPath("[].updatedAt")
                    .description("The last time the user was updated.").type(NUMBER),
                fieldWithPath("[].insertedAt")
                    .description("The date when the user was created.").type(NUMBER),
                fieldWithPath("[].lastToken")
                    .description("The last token associated with the user.").type(STRING),
                fieldWithPath("[].password")
                    .description("The user's password.").type(STRING),
                fieldWithPath("[].persistent")
                    .description("Indicates if the user is using a persistent login.").type(BOOLEAN)
            ),
            responseHeaders(headerWithName("Content-Type")
                .description("The Content-Type of the payload"))));
  }

  @Test
  @DisplayName("Testing GET /api/users with success")
  void getUser() throws Exception {
    // Given
    final Optional<User> optionalUserStub = Optional.of(StubProvider.user());
    final UUID anyUUID = UUID.randomUUID();

    // When
    when(userQuery.getById(anyUUID)).thenReturn(optionalUserStub);

    // Then
    this.mockMvc.perform(get("/api/users/{id}", anyUUID)).andExpect(status().isFound())
        .andDo(this.documentationHandler.document(
            responseFields(
                fieldWithPath("username").description("The username.").type(STRING),
                fieldWithPath("status").description("The user status.").type(NUMBER),
                fieldWithPath("yearOfBirth")
                    .description("The user's year of birth.").type(NUMBER),
                fieldWithPath("email")
                    .description("The user's e-mail.").type(STRING)
            ),
            responseHeaders(headerWithName("Content-Type")
                .description("The Content-Type of the payload"))));
  }
}
