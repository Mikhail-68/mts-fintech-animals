package ru.mts.scheduled;

//@Component
public class ScheduledTask {
//    @Value("${application.animal.directory.result}")
//    private String resultsDirectory;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final OperationsWithAnimalsService operationsWithAnimalsService;
//    private final CreateAnimalService createAnimalService;
//
//    private List<Animal> animals;
//
//    @Autowired
//    public ScheduledTask(OperationsWithAnimalsService operationsWithAnimalsService, CreateAnimalService createAnimalService) {
//        this.operationsWithAnimalsService = operationsWithAnimalsService;
//        this.createAnimalService = createAnimalService;
//    }
//
//    @PostConstruct
//    public void init() {
//        animals = createAnimalService.createListRandomAnimals(10);
//        animals.set(3, animals.get(7));
//        animals.set(8, animals.get(5));
//
////        ScheduledExecutorService es = Executors.newScheduledThreadPool(2);
////        es.scheduleAtFixedRate(() -> {
////            Thread.currentThread().setName("printDuplicatedThread");
////            System.out.println("Current thread: " + Thread.currentThread().getName() +
////                    "\n\t " + operationsWithAnimalsService.findDuplicate(animals));
////        }, 0, 5, TimeUnit.SECONDS);
////        es.scheduleWithFixedDelay(() -> {
////            Thread.currentThread().setName("findAverageAgeThread");
////            System.out.println("Current thread: " + Thread.currentThread().getName() +
////                    "\n\t " + operationsWithAnimalsService.findAverageAge(animals));
////        }, 0, 10, TimeUnit.SECONDS);
//    }
//
//    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
//    public void print() {
//        System.out.println("\n\n");
//        try {
//            System.out.println("\n=== olderAnimals (Deserialization) === ");
//            operationsWithAnimalsService.findOlderAnimal(animals, 1);
//            TypeReference<HashMap<AnimalDto, Integer>> typeRef = new TypeReference<>() {
//            };
//            readFromFile("findOlderAnimal.json", typeRef);
//        } catch (NegativeNumberException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("\n=== leapYearAnimals (Deserialization) === ");
//        operationsWithAnimalsService.findLeapYearNames(animals);
//        TypeReference<HashMap<String, LocalDate>> typeRef1 = new TypeReference<>() {
//        };
//        readFromFile("findLeapYearNames.json", typeRef1);
//
//        System.out.println("\n=== findDuplicate (Deserialization) === ");
//        operationsWithAnimalsService.findDuplicate(animals);
//        TypeReference<HashMap<String, List<AnimalDto>>> typeRef = new TypeReference<>() {
//        };
//        readFromFile("findDuplicate.json", typeRef);
//
//        System.out.println("\n=== findAverageAge (Deserialization) === ");
//        operationsWithAnimalsService.findAverageAge(animals);
//        readFromFile("findAverageAge.json", Integer.class);
//
//        System.out.println("\n=== findOldAndExpensive (Deserialization) === ");
//        operationsWithAnimalsService.findOldAndExpensive(animals);
//        TypeReference<List<AnimalDto>> typeRef3 = new TypeReference<>() {
//        };
//        readFromFile("findOldAndExpensive.json", typeRef3);
//
//        try {
//            System.out.println("\n=== findMinConstAnimals (Deserialization) === ");
//            operationsWithAnimalsService.findMinConstAnimals(createAnimalService.createListRandomAnimals(3));
//            readFromFile("findMinConstAnimals.json", List.class);
//        } catch (NullPointerException e) {
//            System.out.println(e.getClass().getSimpleName());
//        } catch (IllegalArraySizeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private <T> void readFromFile(String file, Class<T> obj) {
//        Path path = Paths.get(resultsDirectory + "/" + file);
//        if (Files.notExists(path)) {
//            return;
//        }
//        try {
//            System.out.println("obj mapper: " + objectMapper.readValue(path.toFile(), obj));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private <T> void readFromFile(String file, TypeReference<T> obj) {
//        Path path = Paths.get(resultsDirectory + "/" + file);
//        if (Files.notExists(path)) {
//            return;
//        }
//        try {
//            System.out.println("obj mapper: " + objectMapper.readValue(path.toFile(), obj));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
