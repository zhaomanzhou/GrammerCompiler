javacc -OUTPUT_DIRECTORY=c-compiler/src/main/java/cn/edu/nwafu/def  c-compiler/src/main/resources/jj/def.jj

rm c-compiler/src/main/java/cn/edu/nwafu/def/ParseException.java
rm c-compiler/src/main/java/cn/edu/nwafu/def/ParserConstants.java
rm c-compiler/src/main/java/cn/edu/nwafu/def/ParserTokenManager.java
rm c-compiler/src/main/java/cn/edu/nwafu/def/SimpleCharStream.java
rm c-compiler/src/main/java/cn/edu/nwafu/def/Token.java
rm c-compiler/src/main/java/cn/edu/nwafu/def/TokenMgrError.java