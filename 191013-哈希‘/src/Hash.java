import java.util.Objects;

public class Hash<K, V> {

    static class Person {
        String sn;  // 学号：学号相同，就是同一个人

        Person(String sn) {
            this.sn = sn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return sn.equals(person.sn);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sn);
        }
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next = null;
    }

    private Node<K, V>[] array = (Node<K, V>[]) new Node[8];
    private int size;

    public V get(K key) {
        // 1. key => int
        // java 中的规定
        int hash = key.hashCode();  // 注意 1 key => int
        int index = hash % array.length;
        Node<K, V> head = array[index];
        for (Node<K, V> cur = head; cur != null; cur = cur.next) {
            if (key.equals(cur.key)) {  // 注意 2
                return cur.value;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Hash<Person, String> hash = new Hash<>();
        Person p = new Person("113");
        Person q = new Person("113");
        System.out.println(p.hashCode());
        System.out.println(q.hashCode());
        //hash.put(p, "莹莹");
        String name = hash.get(q);
        //hash.get(p);
    }
}