
echo "
table=ma_stat_epd_-_F_time
FDW=1
start=1567967400
end=1568053799
column=cpcode,sum(egress_hits),sum(CASE WHEN match_type = 'av' THEN egress_hits ELSE 0 END),sum(CASE WHEN match_type = 'pp' THEN egress_hits ELSE 0 END),sum(CASE WHEN match_type = 'hp' THEN egress_hits ELSE 0 END),sum(CASE WHEN match_type = 'tn' THEN egress_hits ELSE 0 END),sum(CASE WHEN match_type = 'dp' THEN egress_hits ELSE 0 END)
service=htp
objtype=cpcode
object=619770,714707,816139,838505,374045,702701,839033,819425,310950,310851,415580,673180,310852,568682,786948,819158,525019,787338,839541,310860,795911,310941,310943,310945,374043,310947,310948,525020,847554,310949,525021,810716
groupby=cpcode
orderby=2 DESC
limit=10001
END" | nc 198.18.87.199 8457

