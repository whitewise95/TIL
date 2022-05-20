# DTO, DAO, VO에 대해서 쓰는 이유
> 학원과 프로젝트를 하면서 DTO DAO VO를 많이 사용해 보았지만 정확한 뜻을 모르고 사용했었다. 항해99를 하면서 JPA를 사용하면서 Domain과 DTO를 따로 만들어 주면서 DTO에 대해 설명을 해주었다. 이번 계기로 DAO DTO VO에 대해 알고 넘어가면 좋을거같아서 쓰게 되었다.


## DAO
- DAO(Data Access Object) 는 데이터베이스의 data에 접근하기 위한 객체입니다. DataBase에 접근 하기 위한 로직 & 비지니스 로직을 분리하기 위해 사용합니다.
- 프로젝트마다 다르겠지만 보통 Repository 또는 Mapper라고도 클래스명을 정의를 많이 했었다.  

## DTO
- DTO(Data Transfer Object) 는 계층 간 데이터 교환을 하기 위해 사용하는 객체로, DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스)입니다.
- 직접적으로 DB에 닿는 클래스는 domain이고 domain을 사용하다가 실수로 데이터를 잘못건들게 되는경우가 있으니 수정과 삭제 생성을 하기전에는 DTO클래스를 사용한다.

## VO
- VO(Value Object) 값 오브젝트로써 값을 위해 쓰입니다. read-Only 특징(사용하는 도중에 변경 불가능하며 오직 읽기만 가능)을 가집니다.  
- DTO와 유사하지만 DTO는 setter를 가지고 있어 값이 변할 수 있습니다.  
