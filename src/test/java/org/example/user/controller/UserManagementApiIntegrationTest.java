package org.example.user.controller;

import org.example.user.model.User;
import org.example.user.service.UserCommand;
import org.example.user.service.UserManagementService;
import org.example.user.service.UserQuery;
import org.example.user.ConversionHelper;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Execution(SAME_THREAD)
@DisplayName("UserCommandControllerTest: integration tests + doc")
public class UserManagementApiIntegrationTest {

  private MockMvc mockMvc;
  private RestDocumentationResultHandler documentationHandler;

  @MockBean
  UserQuery userQuery;

  @MockBean
  UserCommand userCommand;

  @MockBean
  UserManagementService userManagementService;

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
  @DisplayName("Testing POST /api/users with success")
  void createUser() throws Exception {
    // Given
    final UserRequest userRequestStub = StubProvider.userRequest();

    // When
    when(userManagementService.createUser(any(User.class))).thenReturn(true);

    // Then
    this.mockMvc.perform(post("/api/users")
        .contentType(APPLICATION_JSON)
        .content(ConversionHelper.asJson(userRequestStub))).andExpect(status().isCreated())
        .andDo(this.documentationHandler.document());
  }
}
