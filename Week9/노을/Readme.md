> πΎ κ΄λ ¨μ΄μ: [https://github.com/whiteship/live-study/issues/](https://github.com/whiteship/live-study/issues/8)9

# μλ°μμ μμΈ μ²λ¦¬ λ°©λ² (try, catch, throw, throws, finally)

<br>


- κΈ°λ³Έ μμΈμ²λ¦¬

```java

try(){
 // μμΈκ° λ°μν  κ°λ₯μ±μ΄ μλ μ½λ
}catch(μμΈν΄λμ€1 e){
 // μμΈ λ°μμ μ²λ¦¬ λ‘μ§
}catch(μμΈν΄λμ€2 e2){
 // μμΈ λ°μμ μ²λ¦¬ λ‘μ§
}finally{
 // λ¬΄μ‘°κ±΄ μ€νλλ λ‘μ§μΌλ‘ try() κ΅¬λ¬Έμμ returnμ΄ μμ΄λ μ€νβ¨
}

```

- throw
  - μΈμμ μΌλ‘ μμΈλ₯Ό λ°μμν¬ λ μ¬μ©
```java

if(input == null){
	new throw NullPointException();
}


```

- thorws
  - μμΈκ° λ°μν λ©μλλ₯Ό νΈμΆν κ³³μΌλ‘ μμΈ κ°μ²΄λ₯Ό λκΈ°λ λ°©λ²
  - μμΈκ° λ°μνμ λ ν΄λΉ λ‘μ§μμ `try-catch` λΈλ‘μ μ΄μ©ν΄μ μ²λ¦¬νμ§ μκ³ , νΈμΆν μ§μ μ μμΈλ₯Ό μ λ¬νλ λ°©λ².
  - λͺ©μ μ λ©μλ μν μ€ λ°μν  μ μλ μμΈμ΄λ νΈμΆνλ μΈ‘μμ μ΄λ₯Ό λλΉνλΌλ κ²
```java

public class ExceptionEx16 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        try{
            String fileName = scanner.nextLine();
            createNewFile(fileName);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("μλͺ»λμμ΅λλ€, λ€μ μλ ₯νμΈμ.");
        }
        scanner.close();
    }

    private static void createNewFile(String fileName) throws Exception {
        if(fileName == null || fileName.equals("")) throw new Exception("νμΌμ΄λ¦μ΄ μ ν¨νμ§ μμ΅λλ€.");
        File f = new File(fileName);
        f.createNewFile();
    }
}

```

- try-with-resources

  - **tryμ μμ κ°μ²΄λ₯Ό μ λ¬νλ©΄, try μ½λ λΈλ‘μ΄ λλλ©΄ μλμΌλ‘ μμμ μ’λ£ν΄μ£Όλ κΈ°λ₯**
  - κ΄νΈ()μμ κ°μ²΄λ₯Ό μμ±νλ λ¬Έμ₯μ λ£μΌλ©΄, μ΄ κ°μ²΄λ λ°λ‘ close()λ₯Ό νΈμΆνμ§ μμλ tryλ₯Ό λ²μ΄λλ μκ° μλμ μΌλ‘ close()κ° νΈμΆλλ€.
  - try-with-resourcesλ¬Έμ μν΄ μλμΌλ‘ κ°μ²΄μ close()κ° νΈμΆλ  μ μμΌλ €λ©΄, ν΄λμ€κ° `AutoCloseable` μ΄λΌλ μΈν°νμ΄μ€λ₯Ό κ΅¬νν κ²μ΄μ΄μΌ νλ€.
  - BufferedInputStream, FileInputStreamμ μ΄λ―Έ `AutoCloseable`  μ κ΅¬νμ²΄λ‘ μΆκ°μ μΌλ‘ μΈν°νμ΄μ€λ₯Ό κ΅¬νν  νμκ° μλ€.
  - [InputStream](https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html)μ AutoCloseableλ₯Ό μμλ°μ Closeableμ κ΅¬νν¨


<br>


# μλ°κ° μ κ³΅νλ μμΈ κ³μΈ΅ κ΅¬μ‘°

![](https://images.velog.io/images/san/post/3bf30d85-e8ee-44cc-9c96-ce10a68d8c94/image.png)


|   κ΅¬λΆ    |                     Checked Exception                     |              Unchecked Exception              |
| :-------: | :-------------------------------------------------------: | :-------------------------------------------: |
| νμΈ μμ  |                        μ»΄νμΌ μμ                         |                  λ°νμ μμ                   |
| μ²λ¦¬ μ¬λΆ |                λ°λμ μμΈμ²λ¦¬λ₯Ό ν΄μΌνλ€.                |         λͺμμ μΌλ‘ νμ§ μμλ λλ€.          |
|   μ’λ₯    | Exception<br />IOException<br />ClassNotFoundException λ± | RuntimeException<br />NullPointerException λ± |


- μ΄λ―Έμ§ μΆμ²: https://madplay.github.io/post/java-checked-unchecked-exceptions

# μμΈ μ²λ¦¬ κΈ°μ€
   - μμ§ μλͺ¨λ₯΄κ² λ€. (λμ€μ μ λ¦¬γ )
   - https://velog.io/@janeljs/codesquad-tech-talk-1


# μμΈ μ²λ¦¬ μΌλ°μ μΈ λ°©λ² 3κ°μ§
   - μμΈ λ³΅κ΅¬
     - λ€λ₯Έ μμ νλ¦μΌλ‘ μ λ
     - μμΈκ° λ°μν΄λ μ νλ¦¬μΌμ΄μμ μ μ νλ¦μΌλ‘ μ§ν
     - ex) λ€νΈμν¬ λλ μ΄
   - μμΈμ²λ¦¬ ννΌ
     - μ²λ¦¬νμ§ μκ³  νΈμΆν μͺ½μΌλ‘ μμΈ μ λ¬
     - ex) throws
   - μμΈμ ν
     - λͺνν μλ―Έμ μμΈλ‘ μ ν ν μμΈ μ λ¬
     - ex) checked exception -> uncehcked excpetionμ ν

# μμΈμ²λ¦¬ λΉμ©
- λΉμΈλ€. πΈπΈ
- try-catch λΈλ‘μ κ²μ¬λ‘μ§
- Throwable μμ±μμ fillInStackTrace() λ©μλ (μ£ΌμμΈ!)
   - λ©λͺ¨λ¦¬ μμ­μ Stack μ μμ¬μλ Stack Frame λ€μ pop νμ¬ μΆλ ₯ν΄μ£ΌκΈ° λλ¬Έμ.
 - λΆκΈ° λλ λ¦¬ν΄ λ‘μ§μΌλ‘ ν΄κ²°ν  μ μλ κ²½μ°μλ Exceptionμ μ¬μ©νμ§ μλ κ² μ΄μ’λ€.


# Exceptionκ³Ό Errorμ μ°¨μ΄λ?

- μ€λ₯
  - μμ€ν λ λ²¨μμ λ°μνκΈ° λλ¬Έμ μ¬κ°ν μμ€μ μ€λ₯
  - κ°λ°μκ° λ―Έλ¦¬ μμΈ‘νμ¬ μ²λ¦¬ν  μ μμ
- μμΈ
  - κ°λ°μκ° κ΅¬νν λ‘μ§μμ λ°μ
  - λ°μ μν©μ λ―Έλ¦¬ μμΈ‘νμ¬ μ²λ¦¬ν  μ μμ.


# RuntimeExceptionκ³Ό REκ° μλ κ²μ μ°¨μ΄λ?

- checked excpetion? 
- μλͺ¨λ₯΄κ² μ


# μ»€μ€νν μμΈ λ§λλ λ°©λ²
 - Exception λλ ReuntimeExceptionμ μμ λ°μμ κ΅¬ν

```java
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
```

# μ»€μ€ν μμΈλ₯Ό λ§λ€λ μ°Έκ³ ν΄μΌν  4κ°μ§

1. μλ°μ νμ€ μμΈλ€μ ν¬ν¨λμ§ μλ μ λ³΄λ κΈ°λ₯μ μ κ³΅
2. ExceptionμΌλ‘ λλλ μμΈ ν΄λμ€ λ€μ΄λ° μ»¨λ²€μ λ°λ₯΄κΈ°
3. μμΈκ° λ°μν  μλ μλ μν©κ³Ό μμΈμ μΌλ°μ μΈ μλ―Έλ₯Ό Javadoc μΌλ‘ λ¬Έμν
4. μμΈμ μμΈμ μμΈ μμ±μμκ² μ κ³΅


<br>
<br>


# Reference.

- https://www.notion.so/3565a9689f714638af34125cbb8abbe8
- https://sujl95.tistory.com/62
- https://wisdom-and-record.tistory.com/46
- https://madplay.github.io/post/java-checked-unchecked-exceptions
- https://blog.naver.com/sthwin/221144722072