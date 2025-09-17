//package com.carlos.javajna.jna;
//
//import com.sun.jna.Structure;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class Structs {
//
//	public static class Person extends Structure {
//		public int age;
//		public byte[] name = new byte[64];
//
//		@Override
//		protected List<String> getFieldOrder() {
//			return Arrays.asList("age", "name");
//		}
//
//		public static class ByReference extends Person implements Structure.ByReference{}
//		public static class ByValue extends Person implements Structure.ByValue{}
//	}
//}
