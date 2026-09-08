W# JavaSE 核心笔记·第一卷

> 基础语法 → 面向对象 → 常用API

---

## 第一阶段：环境与执行原理

1. **编译型 vs 解释型**：Java 先编译（`javac` 生成 `.class` 字节码）后解释（JVM 逐行转为机器码）。"一次编写，到处运行"依赖不同操作系统的 JVM。
2. **JDK vs JRE**：JDK = JRE + 开发工具（`javac`、`jar`）；JRE = JVM + 核心类库。开发装 JDK，运行装 JRE。
3. **环境变量**：
   - `JAVA_HOME`：指向 JDK 安装目录（供 Tomcat、IDEA 识别）。
   - `PATH`：添加 `%JAVA_HOME%\bin`，使 `java` 和 `javac` 命令全局可用。
4. **HelloWorld 流程**：`.java` 源文件 → `javac` 编译 → `.class` 字节码 → `java` 运行（注意：运行命令**不要**加 `.class` 后缀）。
5. **IDE（IntelliJ IDEA）**：Project（总工程）→ Module（模块）→ Package（包，倒置域名如 `com.xxx.demo`）→ Class（类），绿色按钮自动编译运行。

---

## 第二阶段：变量与数据类型

1. **标识符规则**：字母/数字/_/$ 组成，**不能数字开头**；严格区分大小写；类名大驼峰（`HelloWorld`），变量/方法小驼峰（`studentName`）。
2. **8 种基本类型**（存栈，直接存值）：
   - 整数：`byte`(1B), `short`(2B), **`int`(4B, 默认首选)**, `long`(8B, 赋值加 `L`)。
   - 浮点：`float`(4B, 赋值加 `F`), **`double`(8B, 默认首选)**。**注意**：浮点数运算有精度误差（如 `0.1+0.2`），比较不能用 `==`。
   - 字符：`char`(2B, 单引号，存 Unicode)。
   - 布尔：`boolean`(仅 `true`/`false`，不能 0/1 代替)。
3. **引用类型**（存栈，存堆内存地址）：类、接口、数组、`String`。默认值为 `null`。
4. **类型转换**：
   - 自动（小转大）：`byte→short→int→long→float→double`。
   - 强制（大转小）：`(int)3.14` → 3（截断小数），注意溢出风险（如 `(byte)128` 结果为 `-128`）。
5. **Scanner 坑**：`nextInt()` 后接 `nextLine()` 会被回车符跳过，需在中间加一个 `sc.nextLine()` 吃掉换行符。

---

## 第三阶段：运算与流程控制

1. **运算符**：
   - `+`：数字相加，字符串连接（`"1"+2` = `"12"`）。
   - `/`：整数相除截断小数（`10/3=3`），需转为浮点（`10.0/3`）。
   - `%`：取余（判断奇偶、取个位数）。
   - **短路逻辑**：`&&`（左假右不判），`||`（左真右不判）。利用短路防空指针：`str != null && str.equals("ok")`。
2. **三元运算符**：`条件 ? 值1 : 值2`，必须接收返回值。
3. **选择结构**：
   - `if-else if`：适合**区间判断**。
   - `switch-case`：适合**等值判断**，支持 `int`/`char`/`String`/枚举。**注意穿透（Fall-Through）**，漏写 `break` 会继续执行下一 `case`。JDK14+ 可用 `->` 自动阻断。
4. **循环结构**：
   - `for`：知道循环次数（计数循环）。
   - `while`：不知道次数，条件触发。
   - `do-while`：至少执行一次。
5. **跳转控制**：
   - `break`：跳出当前循环。
   - `continue`：跳过本次循环，进入下次判断。
   - `return`：结束整个方法。
   - **嵌套循环跳出**：可用带标签的 `break outer;`。

---

## 第四阶段：数组与方法

1. **数组**：连续内存空间，存同类型数据。索引从 `0` 到 `length-1`。
   - 动态初始化：`int[] arr = new int[5];`（默认值 0）。
   - 静态初始化：`int[] arr = {1,2,3};`。
   - 异常：`ArrayIndexOutOfBoundsException`（越界）、`NullPointerException`（空指针操作）。
2. **二维数组**：数组的数组，内层长度可不一致（锯齿数组）。遍历用双重 `for`。
3. **方法定义**：`[修饰符] 返回值类型 方法名(参数列表) { 方法体 }`。
4. **值传递（核心难点）**：Java 只有值传递。
   - **基本类型**：传数值副本，方法内修改**不影响**实参。
   - **引用类型**：传地址值副本，形参和实参指向同一对象，方法内修改属性**影响**外部对象（但 `形参 = new Xxx()` 不影响外部）。
5. **重载（Overload）**：同类中同名，参数列表不同（个数/类型/顺序），**与返回值无关**。
6. **递归**：方法调自己，必须满足：①基线条件（停止）②递推方向（靠近基线）。注意栈溢出风险（`StackOverflowError`）。

---

## 第五阶段：面向对象·上（类与对象）

1. **类 vs 对象**：类是图纸（抽象），对象是实物（具体，存在堆中）。`new` 关键字创建对象。
2. **成员变量 vs 局部变量**：
   - 成员变量（类内方法外）：属于对象，有默认值，存在堆中。
   - 局部变量（方法内/参数）：属于方法，**无默认值**，必须手动赋值，存在栈中。
3. **构造方法**：名字与类同名，无返回值（连 `void` 都没）。
   - **默认无参构造**：若未定义任何构造器，系统提供空构造。
   - **重载**：可定义有参构造。**注意**：一旦定义有参构造，默认无参构造失效（建议手动显式写出无参构造）。
4. **封装（`private` + getter/setter）**：
   - 用 `private` 修饰属性，保护内部数据。
   - 提供 `public` 的 `getXxx()` 和 `setXxx()`，在 `set` 方法内添加业务校验（如年龄不能为负）。
5. **`this` 关键字**：
   - 区分成员变量和局部变量（`this.name = name`）。
   - 构造器间调用：`this(参数)`，**必须写在第一行**。

---

## 第六阶段：面向对象·下（继承、多态、抽象、接口）

1. **继承（`extends`）**：子类继承父类非私有成员。`super()` 调用父类构造器，**必须写在子类构造器第一行**（先有爹后有儿子）。
2. **`super`**：访问父类构造器（`super(...)`）、访问父类方法/属性（`super.方法()`）。
3. **重写（`@Override`）**：子类重新定义父类方法。规则：方法签名相同，访问权限不能更严格，返回值不能变（协变除外）。
4. **多态（核心）**：`父类引用 = 子类对象`。
   - **编译看左边**（变量类型决定能调哪些方法）。
   - **运行看右边**（实际执行子类重写的方法）。
   - **注意**：**属性没有多态**！访问属性只看编译类型（左边）。
5. **`final`**：修饰类（不可继承）、修饰方法（不可重写）、修饰变量（变常量，只能赋值一次）。
6. **抽象类（`abstract`）**：不能实例化（不能 `new`）。抽象方法（无方法体）强制子类重写。
7. **接口（`interface`）**：体现"能力契约"（能做什么）。
   - 属性默认 `public static final`，方法默认 `public abstract`。
   - 用 `implements` 实现，**可多实现**（弥补单继承缺陷）。
   - JDK8+ 支持 `default` 和 `static` 方法。

---

## 第七阶段：常用 API 与工具类

1. **`String`（重中之重）**：
   - **不可变性**：任何修改操作（`substring`、`toUpperCase`）都会产生**新对象**，原串不变。
   - **常量池**：直接双引号赋值（`"abc"`）复用池中对象；`new String("abc")` 强制在堆中新建对象。
   - **`==` vs `equals()`**：`==` 比较栈地址；`equals()` 比较内容（`String` 已重写）。
2. **`StringBuilder` / `StringBuffer`**：可变字符串，避免频繁拼接产生垃圾。
   - `StringBuilder`：线程不安全，效率高（开发首选）。
   - `StringBuffer`：线程安全，效率低（方法有 `synchronized` 锁）。
3. **包装类**：`Integer`、`Double` 等。
   - 自动装箱/拆箱（`Integer i = 100; int n = i;`）。
   - **缓存池陷阱**：`Integer` 在 -128~127 范围内复用对象（`==` 返回 `true`），超出范围 `==` 返回 `false`，必须用 `equals()`。
4. **日期时间（JDK8+）**：`LocalDate`、`LocalTime`、`LocalDateTime`（不可变，线程安全）。
   - 常用：`now()`、`of()`、`plusDays()`、`format(DateTimeFormatter)`。
5. **`Math` 类（静态工具）**：
   - `random()`：返回 `[0.0, 1.0)` 随机 `double`。
   - `abs()`：注意 `Integer.MIN_VALUE` 取绝对值溢出陷阱。
   - `pow()`、`sqrt()`、`round()`（四舍五入）。
6. **`Arrays` 类（数组工具）**：
   - `toString(arr)`：打印数组内容。
   - `sort(arr)`：原地排序（快速排序）。
   - `binarySearch(arr, key)`：二分查找，**前提是有序**。找不到返回 `-(插入点 + 1)`。
   - `copyOf(arr, newLength)`：数组扩容。
   - `asList(arr)`：转集合，**注意**：该集合是视图，不可增删元素。

---

> 第一卷笔记完毕。涵盖了从环境搭建到面向对象、常用API的所有核心骨架。

---

# JavaSE 核心笔记·第二卷

> 异常体系 → JVM底层

---

## 第八阶段：异常处理（Exception）

1. **异常体系（Throwable）**：
   - `Error`（错误）：JVM 内部严重问题（如 `OutOfMemoryError`、`StackOverflowError`），程序无法处理。
   - `Exception`（异常）：程序逻辑或外界因素导致的问题，需处理。
     - **运行时异常（Unchecked）**：继承 `RuntimeException`。编译器不强制处理，多由代码逻辑缺陷导致（`NullPointerException`、`ArrayIndexOutOfBoundsException`、`ArithmeticException`）。
     - **编译时异常（Checked）**：继承 `Exception` 但不继承 `RuntimeException`。编译器强制处理（`try-catch` 或 `throws`），代表外部不可控因素（`IOException`、`SQLException`）。
2. **异常处理方式**：
   - `try-catch-finally`：
     - `catch` 块顺序：子类异常在上，父类异常在下（否则编译报错）。
     - `finally`：无论是否异常、是否 `return`，一定会执行（除非 JVM 退出）。切记：`finally` 块中不要写 `return`，否则会覆盖 `try` 中的异常或返回值。
   - `throws`（甩锅）：在方法签名上声明，告知调用者此方法可能抛出异常。
   - `throw`（主动发射）：在方法体内部手动抛出异常对象（如 `throw new IllegalArgumentException("参数错误")`）。
3. **自定义异常**：继承 `RuntimeException`（常用）或 `Exception`，提供无参构造和带 `message` 的构造，调用 `super(message)`。
4. **try-with-resources（JDK7+）**：
   - 自动关闭实现了 `AutoCloseable` 的资源（流、连接），替代冗余的 `finally` 关闭代码。
   - 支持受抑制异常（Suppressed）：`try` 块抛异常 A，`close()` 抛异常 B，最终异常为 A，B 可通过 `e.getSuppressed()` 获取（避免原始异常被覆盖）。

---

## 第九阶段：集合框架·上（List & Set）

1. **集合与数组区别**：集合动态扩容，只能存引用类型（基本类型通过自动装箱）；数组长度固定，可存基本类型。
2. **Collection 体系**：
   - `List`（有序、可重复、有索引）：
     - `ArrayList`：底层数组，查询（`get`）极快 O(1)，增删中间元素慢 O(n)。开发首选。
     - `LinkedList`：底层双向链表，增删首尾快 O(1)，查询慢 O(n)。
   - `Set`（无序、不可重复）：
     - `HashSet`：底层哈希表。去重依赖元素的 `hashCode()` 和 `equals()`（两者必须同时重写）。不保证存取顺序。
3. **遍历方式**：
   - 普通 `for`（带索引，仅 `List`）。
   - 增强 `for`（`for (类型 变量 : 集合)`）。
   - 迭代器 `Iterator`：通用遍历，支持在遍历时安全删除（`it.remove()`）。

---

## 第十阶段：泛型与迭代（Generic & Iterator）

1. **泛型（`<T>`）**：
   - 核心作用：将运行时的类型转换异常（`ClassCastException`）提前到编译期，并消除显式强转。
   - **泛型通配符（`?`）——笔试高频**：
     - `? extends T`（上界）：表示 `T` 或 `T` 的子类。用于读取（Producer），不能往里写（除非 `null`）。
     - `? super T`（下界）：表示 `T` 或 `T` 的父类。用于写入（Consumer），取出只能用 `Object` 接收。
     - **记忆口诀（PECS）**：Producer Extends, Consumer Super。
2. **迭代器安全删除**：
   - 增强 `for` 循环底层依赖迭代器，但遍历时若直接调用 `集合.remove()`，会触发 `ConcurrentModificationException`。
   - 正确做法：使用 `Iterator` 的 `remove()` 方法，它会同步修改自身状态，避免异常。

---

## 第十一阶段：集合框架·下（Map 体系）

1. **`Map` 特征**：存储键值对（`Key=Value`）。Key 不可重复（重复则覆盖旧 Value），Value 可重复。
2. **`HashMap`（开发首选）**：
   - 底层：数组 + 链表/红黑树（JDK8+）。查询/存取时间复杂度 O(1)。
   - 去重：Key 必须重写 `hashCode()` 和 `equals()`。
   - 允许 `null`：允许一个 `null` Key 和无数个 `null` Value（区别于线程安全的 `Hashtable`）。
   - 扩容机制：默认初始容量 16，负载因子 0.75。元素数量 > 12 时扩容为原来的 2 倍。
   - 遍历：`entrySet()`（同时取 Key/Value）或 `keySet()`（只取 Key）。
3. **高频陷阱**：
   - `put` 方法返回值：返回被覆盖的旧 Value（若无旧值则返回 `null`）。
   - 判断 Key 是否存在用 `containsKey()`，而非 `get() == null`（因为 Value 可能本身就是 `null`）。

---

## 第十二阶段：IO 流（文件读写与序列化）

1. **`File` 类**：仅代表文件/目录路径，不涉及内容读写。常用：`exists()`、`isFile()`、`mkdirs()`、`createNewFile()`。
2. **流分类**：
   - 字节流（万能）：`InputStream` / `OutputStream`。处理图片、视频、音频等二进制文件。
   - 字符流（纯文本）：`Reader` / `Writer`。处理文本，避免乱码。
3. **缓冲流（高效）**：
   - `BufferedReader`：`readLine()` 一次读一行（开发必用）。
   - `BufferedWriter`：`newLine()` 跨平台换行。
4. **对象序列化（内存 → 硬盘）**：
   - 类必须实现 `Serializable` 接口（标记接口）。
   - 使用 `ObjectOutputStream`（写）和 `ObjectInputStream`（读）。
   - `transient`：修饰的属性不参与序列化（如密码），反序列化后取默认值。
   - `serialVersionUID`：显式声明版本号，防止类结构变更后反序列化报 `InvalidClassException`。
5. **资源关闭**：强制使用 `try-with-resources` 自动关闭流，确保资源释放。

---

## 第十三阶段：多线程并发（Concurrency）

1. **进程 vs 线程**：进程是资源分配最小单位，线程是 CPU 调度最小单位。线程共享进程堆和方法区，但拥有独立栈。
2. **线程创建三种方式**：
   - 继承 `Thread`（不推荐，单继承限制）。
   - 实现 `Runnable`（推荐，任务分离）。
   - Lambda 表达式（`new Thread(() -> {...}).start()`）。
3. **核心方法**：
   - `start()`：启动线程（绝不能调 `run()`，否则只是普通方法调用）。
   - `sleep()`：休眠，不释放锁。
   - `join()`：当前线程等待调用者线程结束。
4. **生命周期（六种状态）**：`NEW` → `RUNNABLE`（就绪/运行） → `BLOCKED`（锁竞争）/ `WAITING` / `TIMED_WAITING` → `TERMINATED`。
5. **线程安全（`synchronized`）**：
   - 多线程操作共享数据可能产生脏数据（如 `counter++` 丢失更新）。
   - 同步方法：锁当前对象（`this`）。
   - 同步代码块：粒度更细，锁指定对象。
   - 注意：锁的是对象实例（不同实例锁互不干扰）。
6. **死锁**：线程 A 持锁 L1 等 L2，线程 B 持锁 L2 等 L1，互相僵持。避免策略：约定全局加锁顺序（如总是先 L1 后 L2）。

---

## 第十四阶段（终章）：JVM 内存模型与类加载（进阶内功）

1. **运行时数据区（五大区域）**：
   - **程序计数器**（线程私有）：记录字节码执行行号，唯一无 OOM 的区域。
   - **虚拟机栈**（线程私有）：方法调用栈，存局部变量（基本类型值、引用地址）。
   - **本地方法栈**（线程私有）：为 `native` 方法服务。
   - **堆**（线程共享）：GC 主战场。所有 `new` 出来的对象实例和数组都在这里。
   - **方法区（元空间）**（线程共享）：存类的结构、静态变量（`static`）、常量池（JDK8 后字符串常量池移入堆）。
2. **垃圾回收（GC）判定**：
   - 可达性分析：从 `GC Roots` 出发，无引用链可达的对象被判定为垃圾。
   - GC Roots 包括：栈中引用的对象、静态变量引用的对象、活跃线程等。
3. **`==` vs `equals()` 内存级解释**：
   - `==`：基本类型比数值；引用类型比栈中存的地址。
   - `equals()`：`Object` 默认比地址，但 `String`/`Integer` 等重写为比内容。
4. **双亲委派机制（安全模型）**：
   - 类加载器收到加载请求，先向上委派给父加载器。父类无法加载时，才由子类加载器尝试加载。
   - 好处：保证核心类库（如 `java.lang.String`）不会被用户自定义的同名类篡改覆盖。

---

> 第二卷笔记终。至此 JavaSE 十四阶段知识体系已全部闭环。
