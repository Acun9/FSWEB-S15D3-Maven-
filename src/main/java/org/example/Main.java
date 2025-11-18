package org.example;

import org.example.entity.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Ahmet", "Yilmaz"));
        employees.add(new Employee(2, "Mehmet", "Kaya"));
        employees.add(new Employee(3, "Ayse", "Demir"));
        employees.add(new Employee(2, "Mehmet", "Kaya"));
        employees.add(new Employee(4, "Fatma", "Sari"));
        employees.add(new Employee(3, "Ayse", "Demir"));
        employees.add(new Employee(5, "Ali", "Veli"));

        System.out.println("All employees: " + employees);

        List<Employee> duplicates = findDuplicates(employees);
        System.out.println("Duplicates: " + duplicates);

        Map<Integer, Employee> uniques = findUniques(employees);
        System.out.println("Uniques (one of each duplicate + singles) as map entries: " + uniques);

        List<Employee> onlySingles = removeDuplicates(employees);
        System.out.println("Only single-occurrence employees (duplicates fully removed): " + onlySingles);

        String text = "When the offensive resumed, the Turks received their first victory when the Greeks encountered stiff resistance in the battles of First and Second İnönü," +
                " due to İsmet Pasha's organization of an irregular militia into a regular army. " +
                " The two victories led to Allied proposals to amend the Treaty of Sèvres where both Ankara and Istanbul were represented, but Greece refused." +
                " With the conclusion of the Southern and Eastern fronts, Ankara was able to concentrate more forces on the West against the Greeks." +
                " They also began to receive support from Soviet Union, as well as France and Italy, who sought to check British influence in the Near East.\n" +
                " June–July 1921 saw heavy fighting in the Battle of Kütahya-Eskişehir. While it was an eventual Greek victory, the Turkish army withdrew in good order to the Sakarya river, their last line of defence." +
                " Mustafa Kemal Pasha replaced İsmet Pasha after the defeat as commander in chief as well as his political duties." +
                " The decision was made in the Greek military command to march on the nationalist capital of Ankara to force Mustafa Kemal to the negotiating table." +
                " For 21 days, the Turks and Greeks fought a pitched battle at the Sakarya river, which ended in Greek withdrawal." +
                " Almost of year of stalemate without much fighting followed, during which Greek moral and discipline faltered while Turkish strength increased." +
                " French and Italian forces evacuated from Anatolia. The Allies offered an armistice to the Turks, which Mustafa Kemal refused.";

        Map<String, Integer> wordCount = WordCounter.calculateWord(text);
        System.out.println("Sample word count (top 10 by frequency):");
        wordCount.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }

    // findDuplicates: listede birden fazla görünen çalışanları döndürür (birer tane olarak), null değerleri yoksayar
    public static List<Employee> findDuplicates(List<Employee> list) {
        if (list == null) return Collections.emptyList();
        Map<Employee, Integer> counts = new LinkedHashMap<>();
        for (Employee e : list) {
            if (e == null) continue;
            counts.put(e, counts.getOrDefault(e, 0) + 1);
        }
        List<Employee> result = new ArrayList<>();
        for (Map.Entry<Employee, Integer> en : counts.entrySet()) {
            if (en.getValue() > 1) {
                result.add(en.getKey());
            }
        }
        return result;
    }

    // findUniques: tekrar edenlerden sadece bir tanesini ve tek geçenleri bulup bir Map<id, Employee> döndürür
    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Employee> result = new LinkedHashMap<>();
        if (list == null) return result;
        for (Employee e : list) {
            if (e == null) continue;
            // Eğer daha önce bu id eklenmemişse ekle (tekrar edenlerden bir tanesi kalır)
            if (!result.containsKey(e.getId())) {
                result.put(e.getId(), e);
            }
        }
        return result;
    }

    // removeDuplicates: listede bir data birden fazla kez geçiyorsa hepsini siler. Sadece tek geçen kayıtları döndürür
    public static List<Employee> removeDuplicates(List<Employee> list) {
        List<Employee> result = new ArrayList<>();
        if (list == null) return result;
        Map<Integer, Integer> counts = new HashMap<>();
        for (Employee e : list) {
            if (e == null) continue;
            counts.put(e.getId(), counts.getOrDefault(e.getId(), 0) + 1);
        }
        for (Employee e : list) {
            if (e == null) continue;
            if (counts.getOrDefault(e.getId(), 0) == 1) {
                result.add(e);
            }
        }
        return result;
    }
}