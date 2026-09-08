# JavaWeb 笔记 · HTTP 通信地基（第一层）

> **通信地基（HTTP 协议）是所有后端 Bug 的"案发现场"。**
>
> 很多初学者觉得 Spring 的注解搞不定，其实根源往往不是 Spring，而是**根本没看清浏览器到底发了个什么样的 HTTP 包**。
>
> 既然是打地基，不讲晦涩的 RFC 文档，直接站在 **Java 后端（Spring）接收请求的角度**，把 HTTP 拆成 **4 个必须看透的零件**。

---

## 零件一：URL（统一资源定位符）

请求地址。Spring 在解析它时，脑子里只把它分成两部分：

| 部分 | 样子 | Spring 对应 |
| :--- | :--- | :--- |
| **路径（Path）** | `/user/1001`、`/order/create` | `@RequestMapping("/user")` |
| **查询参数（Query String）** | `?` 后面的键值对：`?page=1&size=10` | `@RequestParam("page")` |

**后端核心认知：**

> **路径用来定位资源（找谁干），查询参数用来筛选条件（怎么干）。**
>
> 例如 `GET /orders?status=PAID`，意思是"订单（资源），只要已支付的（条件）"。

---

## 零件二：Method（请求方法）

它不是随便写的，在后端有明确的"潜规则"：

| 方法 | 后端语义（对应 SQL） | Spring 注解 | 幂等性（重点） |
| :--- | :--- | :--- | :--- |
| **GET** | 查询数据（Select） | `@GetMapping` | 是（查多少次结果一样） |
| **POST** | 新增数据（Insert） | `@PostMapping` | 否（每发一次就新建一条） |
| **PUT** | 全量修改（Update） | `@PutMapping` | 是（每次把整个对象覆盖） |
| **DELETE** | 删除数据（Delete） | `@DeleteMapping` | 是（删一次和删十次结果都是没了） |
| **PATCH** | 局部修改 | `@PatchMapping` | 否 |

> ⚠️ **新手巨坑**：浏览器 Form 表单只支持 GET 和 POST。如果你想测试 PUT / DELETE，**千万别用浏览器地址栏**，必须用 **Postman 或 Apifox**，否则会报 **405** 错误。

---

## 零件三：Headers（请求头）—— 最关键的"幕后信息"

Headers 是键值对（Key-Value），不展示业务数据，但决定了**后端能不能正常解析**。以下 3 个头必须记住：

### 1. `Content-Type`（内容类型）—— 后端解析器的"开关"

Java 后端开发中最容易出错的头，**没有之一**。它告诉后端："我请求体（Body）里的数据是什么格式？"

| Content-Type | 含义 | Spring 接收方式 |
| :--- | :--- | :--- |
| `application/x-www-form-urlencoded` | 传统表单格式（`key1=value1&key2=value2`） | `@RequestParam` |
| `application/json` | **前后端分离主流**（`{"name":"jack"}`） | **`@RequestBody`** |
| `multipart/form-data` | 文件上传专用 | `MultipartFile` |

> ⚠️ 如果前端发的是 JSON，但你后端用 `@RequestParam` 接，**必报 `415 Unsupported Media Type` 或 `400` 错误**。

### 2. `Authorization`（认证令牌）

存放 JWT 登录态（如 `Bearer xxxx.xxxx.xxxx`）。后端用 `@RequestHeader("Authorization")` 提取。

### 3. `Cookie`（会话小纸条）

存放浏览器自动携带的 SessionId。后端通过 `@CookieValue` 提取。

---

## 零件四：Body（请求体）

真正传输数据的地方。Java 后端只需区分两种情况：

- **有 Body**：POST / PUT / PATCH 请求才有。如果是 JSON 格式，Spring 会通过 **`HttpMessageConverter`（消息转换器）** 自动把 JSON 捏成 Java 对象（POJO）。
- **无 Body**：GET / DELETE 请求绝对没有 Body。所有参数要么拼在 URL 路径里，要么放在 Query String 里。

---

## 给后端开发者的"抓包神技"

理论说再多，不如亲眼看一眼。打开浏览器（或 IDEA 自带 HTTP Client），按 **F12 → 网络（Network）**，随便点一个请求，对照着看：

- **General**：看 `Request Method` 和 `Status Code`（状态码）。
- **Request Headers**：看 `Content-Type` 和 `Cookie`。
- **Request Payload**：看实际提交的 JSON 结构。

> 当你把 Request Headers 里的 `Content-Type` 和后端 Controller 的注解对上号时，HTTP 第一层你就彻底毕业了。

---

## 学完立刻实战验证

1. 打开浏览器访问 `https://api.github.com/users/octocat`
2. 按 F12 看 Network
3. 观察：Method 是 GET，URL 里 `/users/octocat` 是路径参数
4. 看 Response Headers 里的 `Content-Type: application/json`，说明返回的是 JSON

## 本周任务

凡是遇到接口报 **400（Bad Request）** 或 **415（Unsupported Media Type）**：

> **不要先看代码，先看 F12 里的 Request Headers 中的 Content-Type 是不是和后端 `@RequestBody` 匹配。**

这能解决你 70% 的联调烦恼。

---
