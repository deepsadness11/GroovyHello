/**
 * Groovy的基本语法介绍
 * Created by Administrator on 2017/2/10 0010.
 */

//1.Groovy的注释相同
//2.Groovy语句可以不用分号结尾
//3.变量定义使用def,并且为动态类型，不需要制定类型
def var = 1
def var2 = "I am person"
def int x = 1//定义变量时，也可以直接定义类型

//函数定义是，函数的类型也可以不指定
String testFun(arg1,arg2){}//无需制定参数类型

//除了变量定义可以不指定类型外，Groovy中函数的返回值也可以是无类型的
def nonReturnTypeFun(){
    last_line//最后一行代码执行的结果就是本函数的返回值
}
//制定的返回值时，就可以不用def
String getString(){
    return "I am string"
}

//值得注意的是，最后一行，就是返回值
def getSomeThings(){
    "I get some things"
    1000//如果这是最后一行，则改为返回Integer
}

//还需要注意的是，如果函数中指明了返回值，则必须返回正确的数据类型，否则会报错。
//如果使用了动态类型的胡啊，就可以返回任何类型了


//Groovy对字符串的支持简直逆天
//1.单引号''中的内容严格对应java中的String，不对$符号进行转义
def singleQuote = 'I am $ dollar'  //输出就是I am $ dollar

//2.双引号""的内容，则和脚本语言的处理有点像，如果字符中有$号的话，则会$表达式先求值
def doubleQuoteWithoutDollar ="I am one dollar"
def y = 'one'
def doubleQuoteWithDollar = "I am $y dollar" //输出I am one dollar

//3.三个引号'''xxx'''中的字符串支持随意换行
def multiesLines = '''begin
    line 1
    line 2
    end'''

//最后，除了每行的代码不需要家分号外，Groovy中函数调用的时候，还可以不加括号！！！！
println ("test")
//---->
println "test"

//但是需要小心的是，有时候会混淆到一起，所以还得具体情况具体分析


//Groovy中的数据接口
//1.基本数据类型
y.getClass().getCanonicalName()//所有的基本数据类型都是包装类
//2.容器类
/*
List:对应List接口，一般使用ArrayList作为真正的实现类
Map:底层对应java中的LinkedHashMap
Range:范围，它其实是一个List
 */

//1.List
//变量定义：List由[]定义。比如
def aList = [5,'String',true]   //List由[]定义，其元素是可以任何对象

//变量存取，可以直接通过索引存取，而且不用担心索引越界。如果索引超过了当前链表的长度，
//List会自动添加元素
assert aList[1]=='String'
assert aList[5]==null   //第6个元素为空
aList[1000]=1000 //设置1001个元素的只为1000

assert  aList[1000]==1000
//那么现在有多少元素呢？
println aList.size() //==>1001

//2.Map
//变量定义 [:]
def aMap = ['key1':'value1','key2':'value2']
//key 也可以不用‘’
def aNewMap=[key1:'String',key2:123]
//这里还存在一个混淆
def key3 = "vovo"
def aConfusedMap =[key3:"who an i?"]
//aConfusedMap中的是key3到底是什么呢？答案是 ”key3"
//下面是正确的使用方式
def aConfuseMap= [(key3):"I am vovo"]

//Map中存取元素更加方便
println aMap.keyName //<==这种表达式的key就是aMap的一个成员变量一样
println aMap['keyName']//<==这个表达式更传统一些
aMap.ano='i am map' //<==为Map添加新元素

//3.Range类
//Range类型的变量 由begin值+两个点+end值表示 左边这个aRange包含1,2,3,4,5,
def aRange = 1..5

//如果不想包含最后一个元素
def aRangeWithOutEnd = 1..<5
//可以分别取出开头和接完
println aRange.from
println aRange.to

//Groovy Api的一些秘籍
//Groovy中所有的get set都是 可以 .出来的


//Groovy的闭包Clousure
//闭包是一种数据类型，它代表了一段可以执行的代码。其外形如下
def aClosure={  //闭包是一段代码，所以需要用花括号
    String para1,int para2->    //这个箭头很关键，箭头前面是参数制定，后面是代码
    println 'this is code'  //这是代码，最有一句是返回值
    //也可以使用return
}

//定义闭包后，调用的方法为 闭包对象.call ()  闭包对象(参数)
aClosure.call("this is string ",1001)
aClosure("this is string",100)

//还需要注意的是，如果闭包没有定义参数，则隐藏有一个it 和this一样
def greeting = {"Hello,$it!"}
assert greeting('Cry')=='Hello,Cry'

//但是，如果在闭包定义时，就采用下面这个写法，则表示闭包没有参数！！
//这是默认参数也没有的节奏了吗？！！
def noParamClousure={->true}
//这个时候吗，就不能给其传参数了！！！

//Closure使用过程中的注意点
//1.省略()
//闭包在Groovy中大量的使用，比如很多类都定义了一些函数，这些函数最有的参数都是一个闭包

//这个函数表示针对与每一个元素都调用closure进行处理。有点像回调
//public static <T>List<T>each(List<T> self,Closure closure)

def iamList = [1,2,3,4,5]
iamList.each {  //使用闭包代替了圆括号
    println it
}

//上面代码有两个知识点：
//1.each函数调用的括号不见了。原来，当函数的最后一个参数是闭包时，可以省略括号
def testClosure(int a1,String b1,Closure closure){
    closure()//调用闭包
}
//调用时，可以免括号
testClosure(4,"test",{
    println "I am in closure"
})

//这个特性非常重要！！！

