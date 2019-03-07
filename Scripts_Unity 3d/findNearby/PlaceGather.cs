﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlaceGather:MonoBehaviour {
    

    public List<PlaceInfo> places = new List<PlaceInfo>();

    public void Awake()
    {
        places.Add(new PlaceInfo("教师教育学院", 27.9219485700, 120.6963074400, "温州大学教师教育学院拥有课程与教学论硕士点，应用心理学、教育技术学、小学教育学、学前教育学4个本科专业和五年一贯制学前教育专科专业。其中课程与教学论为校级重点学科，教育技术学专业为省级重点专业。"));
		places.Add(new PlaceInfo("美术与设计学院", 27.9199625700,120.6948014400, "温州大学美术与设计学院现有教职工 51人,其中教授7人、副教授12人，博士1人，硕士9人，在读硕士11人。中国美术家协会会员6人，中国书法家协会会员3人，省级各类协会会员20余人。现有全日制学生400余人。"));
		places.Add(new PlaceInfo("化学与材料工程学院", 27.9228247000, 120.6881231900, "温州大学化学与材料工程学院创建于1958年。学院现有化学、应用化学、材料科学与工程、化学工程与工艺等四个本科专业，有机化学、物理化学、应用化学及化学课程教学论等四个硕士点。"));
		places.Add(new PlaceInfo("步青学区", 27.9234715700, 120.6922254400, "为深化高校人才培养模式改革，2012年6月，温州大学启动“学区制”学生教育管理模式改革。学校在保留学生专业学院属性的同时，以学生住宿分布为划分在全校建立三大学区，步青学区随之成立。"));
        places.Add(new PlaceInfo("建筑工程学院", 27.9204067000, 120.6899091900, "温州大学建筑工程学院创办于1984年，现有土木工程、工程管理、建筑学、道路与桥梁4个本科专业，其中土木工程专业是校级重点专业，于2015年开设硕士学位点。设有温州岩土力学研究所，温州大学房地产研究所，温州市市政园林研究所，建有校级防灾减灾重点A类学科。"));
        places.Add(new PlaceInfo("校史博物馆", 27.9211195700, 120.6911844400, "校史博物馆奉行“公共认知，高山仰止，一个不少”的建设理念，展示了温大历史上的每一所学校及每一所学校的每一位师生，充分挖掘学校发展的文化底蕴，体现大气、大家和大爱，是所有温大人共同的精神家园。"));
		places.Add(new PlaceInfo("数理与电子信息工程学院", 27.9185914548, 120.6930175883, "学院现有数学、统计学、物理学、电气工程、计算机科学与技术、电子科学与技术、信息与通信工程等7个一级学科。为温州大学规模最大的学院。"));
		places.Add(new PlaceInfo("生命与环境科学学院", 27.9230807000, 120.6897271900, "温州大学生命与环境科学学院设有植物学、动物学、综合、环境科学、环境生物学等五个教研室；现有基础生物实验室、基础环境实验室、动物标本陈列室、植物标本陈列室、人体标本陈列室等多个实验室；已成立有生物系统动力学、浙南生物资源、农业生态与生物技术应用等多个研究所。"));
		places.Add(new PlaceInfo("机电工程学院", 27.9200135105,120.6920090777, "温州大学机电工程学院前身为机械与电子工程系，2006年更名为机电工程学院。学院是学校大力建设发展的工科学院"));
        
    }
}
