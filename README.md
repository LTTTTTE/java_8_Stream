# java_8_Stream 

## Java8 에 #Stream #Lambda #FunctionalInterface #Optional #Collectors #Comparator 공부

### Collection.forEach 와 Stream.forEach 의 차이
- 순차순서 <=> 임의순서
- 구조적 간섭이외 값수정 허용 <=> 간섭불허용
- 병렬처리시 collection락 <=> 락없이 간섭불허용에만 의존(비일관적일수있음)
