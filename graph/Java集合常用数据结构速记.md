# Java 集合常用数据结构速记

## 1. 先分清：接口 vs 实现类

Java 里经常这样写：

```java
List<Integer> list = new ArrayList<>();
Queue<Integer> queue = new ArrayDeque<>();
Map<Integer, Integer> map = new HashMap<>();
```

左边通常是接口，表示“我需要什么能力”。

右边通常是实现类，表示“底层具体怎么做”。

| 接口 | 直译 | 常用实现类 | 常见用途 |
| --- | --- | --- | --- |
| `List` | 列表 | `ArrayList`, `LinkedList` | 按顺序存，按下标取 |
| `Queue` | 队列 | `ArrayDeque`, `LinkedList` | 先进先出 |
| `Deque` | 双端队列 | `ArrayDeque`, `LinkedList` | 可当栈，也可当队列 |
| `Set` | 集合 | `HashSet`, `TreeSet` | 去重 |
| `Map` | 映射/哈希表 | `HashMap`, `TreeMap` | key-value 查找 |

## 2. 列表 List

常用写法：

```java
List<Integer> list = new ArrayList<>();
```

含义：

```text
左边 List：我按列表能力使用它
右边 ArrayList：底层用数组实现
```

常用操作：

```java
list.add(10);
list.get(0);
list.remove(0);
list.size();
```

`ArrayList` 特点：

```text
按下标访问快
尾部添加快
中间插入/删除较慢
刷题里最常用
```

## 3. 队列 Queue

常用写法：

```java
Queue<Integer> queue = new ArrayDeque<>();
```

队列特点：

```text
先进先出
先进入的元素先出来
```

常用操作：

```java
queue.offer(1); // 入队
queue.poll();   // 出队
queue.peek();   // 看队头，不删除
queue.isEmpty();
```

为什么更推荐 `ArrayDeque`：

```text
ArrayDeque 底层是循环数组
做队列通常比 LinkedList 更快
对象开销更小
```

## 4. 栈 Stack

不太推荐老的 `Stack` 类，刷题更常用 `Deque`：

```java
Deque<Integer> stack = new ArrayDeque<>();
```

栈特点：

```text
后进先出
最后放进去的元素最先出来
```

常用操作：

```java
stack.push(1); // 入栈
stack.pop();   // 出栈
stack.peek();  // 看栈顶，不删除
stack.isEmpty();
```

## 5. 哈希表 Map

常用写法：

```java
Map<Integer, Integer> map = new HashMap<>();
```

含义：

```text
Map：映射关系
HashMap：用哈希表实现
```

常用操作：

```java
map.put(1, 100);
map.get(1);
map.containsKey(1);
map.getOrDefault(1, 0);
map.remove(1);
```

常见场景：

```text
统计次数
记录位置
快速判断某个 key 是否存在
```

## 6. 集合 Set

常用写法：

```java
Set<Integer> set = new HashSet<>();
```

常用操作：

```java
set.add(1);
set.contains(1);
set.remove(1);
set.size();
```

常见场景：

```text
去重
判断某个元素是否出现过
```

## 7. 为什么左边和右边名字经常不同

比如：

```java
List<Integer> list = new ArrayList<>();
Queue<Integer> queue = new ArrayDeque<>();
Map<Integer, Integer> map = new HashMap<>();
```

这是 Java 的常见写法：

```text
左边写接口：代码只依赖“能力”
右边写实现类：决定底层具体结构
```

好处是以后可以换实现类：

```java
List<Integer> list = new LinkedList<>();
```

只要后面的代码只用了 `List` 的通用方法，就不用大改。

## 8. 刷题常用选择

| 需求 | 推荐写法 |
| --- | --- |
| 普通列表 | `List<Integer> list = new ArrayList<>();` |
| 邻接表 | `List<Integer>[] graph = new ArrayList[n];` |
| 队列 | `Queue<Integer> queue = new ArrayDeque<>();` |
| 栈 | `Deque<Integer> stack = new ArrayDeque<>();` |
| 哈希表 | `Map<Integer, Integer> map = new HashMap<>();` |
| 去重集合 | `Set<Integer> set = new HashSet<>();` |

## 9. 课程表这题里的选择

邻接表：

```java
@SuppressWarnings("unchecked")
List<Integer>[] graph = new ArrayList[numCourses];

for (int i = 0; i < numCourses; i++) {
    graph[i] = new ArrayList<>();
}
```

队列：

```java
Queue<Integer> queue = new ArrayDeque<>();
```

整体含义：

```text
graph[i]：课程 i 学完后，可以解锁哪些课
queue：当前入度为 0、可以学习的课程
```

## 10. 一句话总结

```text
List / Queue / Map / Set / Deque 是能力接口。
ArrayList / ArrayDeque / HashMap / HashSet 是具体实现。
刷题时左边写接口，右边选性能合适的实现类。
```
