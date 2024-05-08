package ru.iskhakov.walletsapi.rest;

import lombok.RequiredArgsConstructor;

//@WebFluxTest
@RequiredArgsConstructor
//@Rollback
//@SpringBootTest
//@Transactional
//@ActiveProfiles("test")
class WalletRestControllerTest {

//    private WebTestClient webTestClient;
//    private final WalletRepositoryService repository;
//    private final WalletSerializer serializer;

    private static final String OPERATION_END_POINT = "/api/v1/wallet";
    private static final String GET_WALLET_END_POINT = "/api/v1/wallets/";

    public static final Integer THREADS = 1000;
    public static final Integer ITERATIONS = 1;

//    @BeforeEach
//    void init(ApplicationContext context) {
//        webTestClient = WebTestClient.bindToApplicationContext(context).build();
//    }
//
//    @Test
//    void shouldFindWalletById() {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(10000))
//                .build();
//        repository.saveWallet(wallet);
//        //when
//        EntityExchangeResult<String> expectedResult = webTestClient.get().uri(GET_WALLET_END_POINT + wallet.getId())
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(String.class)
//                .returnResult();
//        //then
//        assertThat(expectedResult.getResponseBody()).isEqualTo("""
//                {
//                "walletId": "",
//                "amount": "10000"
//                }
//                """);
//    }
//
//    @Test
//    void shouldNotFindWalletById() {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(10000))
//                .build();
//        repository.saveWallet(wallet);
//        //when
//        EntityExchangeResult<String> expectedResult = webTestClient.get().uri(GET_WALLET_END_POINT + "1")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(String.class)
//                .returnResult();
//        //then
//        assertThat(expectedResult.getResponseBody()).isNullOrEmpty();
//    }
//
//    @Test
//    void shouldDepositToWalletById() {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(10000))
//                .build();
//        repository.saveWallet(wallet);
//        final OperationDto operationDto = OperationDto.builder()
//                .walletId(wallet.getId())
//                .operationType(DEPOSIT)
//                .amount(BigDecimal.valueOf(1000))
//                .build();
//        //when
//        webTestClient.post().uri(OPERATION_END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(operationDto))
//                .exchange()
//                .expectStatus().isOk();
//        //then
//        assertThat(repository.findWalletById(wallet.getId()).getWalletBalance()).isEqualTo(BigDecimal.valueOf(11000));
//    }
//
//    @Test
//    void shouldWithdrawFromWalletById() {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(10000))
//                .build();
//        repository.saveWallet(wallet);
//        final OperationDto operationDto = OperationDto.builder()
//                .walletId(wallet.getId())
//                .operationType(WITHDRAW)
//                .amount(BigDecimal.valueOf(1000))
//                .build();
//        //when
//        webTestClient.post().uri(OPERATION_END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(operationDto))
//                .exchange()
//                .expectStatus().isOk();
//        //then
//        assertThat(repository.findWalletById(wallet.getId()).getWalletBalance()).isEqualTo(BigDecimal.valueOf(9000));
//    }
//
//    @Test
//    void shouldBeNoWithdrawalWhenThereAreNotEnoughFounds() {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(1000))
//                .build();
//        repository.saveWallet(wallet);
//        final OperationDto operationDto = OperationDto.builder()
//                .walletId(wallet.getId())
//                .operationType(WITHDRAW)
//                .amount(BigDecimal.valueOf(10000))
//                .build();
//        //when
//        webTestClient.post().uri(OPERATION_END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(operationDto))
//                .exchange()
//                .expectStatus().isOk();
//        //then
//        assertThat(repository.findWalletById(wallet.getId()).getWalletBalance()).isEqualTo(BigDecimal.valueOf(1000));
//    }
//
//    @Test
//    void shouldNotFindOperation() {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(10000))
//                .build();
//        repository.saveWallet(wallet);
//        final OperationDto operationDto = OperationDto.builder()
//                .walletId(wallet.getId())
//                .operationType(UNDEFINED)
//                .amount(BigDecimal.valueOf(1000))
//                .build();
//        //when
//        webTestClient.post().uri(OPERATION_END_POINT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(operationDto))
//                .exchange()
//                .expectStatus().isOk();
//        //then
//        assertThat(repository.findWalletById(wallet.getId()).getWalletBalance()).isEqualTo(BigDecimal.valueOf(10000));
//    }
//
//    @Test
//    void shouldDepositToWalletByIdLoadTesting() throws IOException {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(10000))
//                .build();
//        repository.saveWallet(wallet);
//        final OperationDto operationDto = OperationDto.builder()
//                .walletId(wallet.getId())
//                .operationType(DEPOSIT)
//                .amount(BigDecimal.valueOf(100))
//                .build();
//        //when
//        TestPlanStats stats = testPlan(
//                threadGroup(THREADS, ITERATIONS,
//                        httpSampler(OPERATION_END_POINT + wallet.getId())
//                                .method(HTTPConstants.POST)
//                                .post(serializer.serialize(operationDto), ContentType.APPLICATION_JSON))
//        ).run();
//        //then
//        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
//        assertThat(repository.findWalletById(wallet.getId()).getWalletBalance()).isEqualTo(BigDecimal.valueOf(1010000));
//    }
//
//    @Test
//    void shouldDepositToWithdrawByIdLoadTesting() throws IOException {
//        //given
//        final Wallet wallet = Wallet.builder()
//                .walletBalance(BigDecimal.valueOf(10000))
//                .build();
//        repository.saveWallet(wallet);
//        final OperationDto operationDto = OperationDto.builder()
//                .walletId(wallet.getId())
//                .operationType(WITHDRAW)
//                .amount(BigDecimal.valueOf(1))
//                .build();
//        //when
//        TestPlanStats stats = testPlan(
//                threadGroup(THREADS, ITERATIONS,
//                        httpSampler(OPERATION_END_POINT + wallet.getId())
//                                .method(HTTPConstants.POST)
//                                .post(serializer.serialize(operationDto), ContentType.APPLICATION_JSON))
//        ).run();
//        //then
//        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
//        assertThat(repository.findWalletById(wallet.getId()).getWalletBalance()).isEqualTo(BigDecimal.valueOf(9000));
//    }
}